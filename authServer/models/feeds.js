const dbcp = require ('./dbcp');

exports.addFeed = (feed)=>{
  console.log(feed);
  
  const sql = 'INSERT INTO feed(writer, picture, comment) VALUES (?,?,?)';
  const query = (conn)=>{
    const p = new Promise((resolve, reject)=> {
      conn
        .query(sql, [feed.writer, feed.picture, feed.comment])
        .then((result)=> {
          conn.end()
          console.log(result)
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
  // 필요한거 user.id, user.name, user.photo, feed.picture, feed.comment, feed.time
  const sql = "SELECT user.id, user.name,  feed.picture, feed.comment, feed.time FROM user INNER JOIN feed ON feed.writer=user.id";
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