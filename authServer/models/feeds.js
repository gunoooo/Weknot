const dbcp = require ('./dbcp');

exports.addFeed = (feed)=>{
  //console.log(feed);
  
  const sql = 'INSERT INTO feed(writer, picture, comment) VALUES (?,?,?)';
  const query = (conn)=>{
    const p = new Promise((resolve, reject)=> {
      conn
        .query(sql, [feed.writer, feed.picture, feed.comment])
        .then((result)=> {
          conn.end()
          //console.log(result)
          resolve(result)
        })
        .catch((err) =>{reject(err)})
    })
    return p;
  }

  const p = new Promise((resolve, reject) => {
    dbcp.getConnection()
      .then(query)
      .then((result=> {
        resolve(result)
      }))
      .catch((err) => {reject(err)})
  })
  return p;
};

exports.showUserProfile = async (cid) => {
  let conn;
  const sql = "SELECT user.*, feed.* FROM user INNER JOIN feed ON feed.writer=user.id WHERE user.id = ?";
  let result;
  try{
    conn = await dbcp.getConnection();
    const [rows, fields] = await conn.query(sql,[cid]);
    result = rows;
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
};

exports.getFeeds = async (id) => {
  let conn;
  // 내꺼랑 친구꺼 피드만 보내기 (현재는 모든사용자 피드)
  // 필요한거 user.photo 좋아요 갯수, 내가 좋아요했는지 or 안했는지
  const sql = "SELECT user.id, user.name,  feed.picture, feed.comment, feed.time FROM user INNER JOIN feed ON feed.writer=user.id order by feed.time desc";
  let result;
  try{
    conn = await dbcp.getConnection();
    result = await conn.query(sql);
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
}

exports.getOnlyFriends = async (id) => {

}

exports.getLike = async (userId, feedId) => {
  let conn;
  const sql = "select * from `like` join feed on like.receiver = feed.writer where sender=? and feed.id=?";
  let result;
  try{
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [userId, feedId]);
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
}

exports.addLike = async (userId, feedId) => {
  let conn;
  const sql = "insert into `like` (sender, feedId, receiver) select ? as sender, ? as feedId, writer as receiver from feed where id=?";
  let result;
  try{
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [userId, feedId, feedId]);
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
}

exports.deleteLike = async (likeId) => {
  let conn;
  const sql = "delete * from `like` where id=?";
  let result;
  try{
    conn = await dbcp.getConnection();
    result = await conn.query(sql, likeId);
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
}