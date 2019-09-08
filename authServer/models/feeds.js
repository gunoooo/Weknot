const dbcp = require ('./dbcp');

exports.addFeed = (req, res, feed)=>{

};

exports.showUserProfile = async (cid) => {
  let conn;
  const sql = "SELECT user.id, user.name, user.birth,user.scope, user.intro, user.picture, user.point, user.gender, feed.id, feed.writer, feed.time, feed.picture, feed.`comment` FROM user INNER JOIN feed ON feed.writer=user.id WHERE user.id = 1111"
  let result;
  try{
    conn = await dbcp.getConnection();
    const [rows, fields] = await conn.query(sql,cid);
    result = rows;
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
};
