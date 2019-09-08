const dbcp = require ('./dbcp');

exports.addFeed = (req, res, feed)=>{

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
