const dbcp = require ('./dbcp');
const jwt = require('jsonwebtoken');

/*exports.getQueryUser = async (cid) => {
  let conn;
  let result;
  try{
    conn = await dbcp.getConnection();
    const [rows, fields] = await conn.query('SELECT * FROM user WHERE id=? ', [cid]);
    result = rows;
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return result
  }
};//일단 전체적으로 리턴하고 전체적으로 할 수 없다 한다면 auth.js에서 queryUser을 두번 쓴 것을 
//나누어서 사용할것.
*/
exports.loginUser = (req,res,user) => {
  const secret = req.app.get('jwt-secret');

  const sign = (user) => {
    const p = new Promise((resolve, reject) => {
      let exptime = Math.floor(Date.now() / 1000) + (60*60*24); // 24 hour
      jwt.sign(
        {
          userName: user.name,
          exp: exptime,
          iss: 'weknot',
          sub: user.id,
          aud: 'weknot',
          iat: Math.floor(Date.now() / 1000),
        },
        secret,
        (err, token) => {
          if (err) reject(err)
          resolve(token)
        })
      })
      return p;
  }
   return sign(user);
}
exports.getQueryUser = (cid) => {
  const query = (conn)=>{
    const p = new Promise((resolve, reject)=> {
      conn
        .query('SELECT * FROM user WHERE id=?', [cid])
        .then((result)=> {
          conn.end()
          resolve(result)
        })
        .catch((err) =>{
          reject(err)})
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

exports.allUsers = async () => {
  let conn;
  let result;
  try{
    conn = await dbcp.getConnection();
    result = await conn.query("select * from user ");
  }catch(err){
    throw err;
  }finally{
    if(conn) await conn.end();
    return result;
  }
};

/*exports.registerUser = async (user) => {
  let conn;
  const sql = "INSERT INTO user(id,name,password,birth,gender,phoneNumber) VALUES (?,?,?,?,?,?) ";
  let isSuccess = true;
  try{
    conn = dbcp.getConnection();
    await conn.query(sql,user);
  }catch (error){
    console.log(error);
    isSuccess = !isSuccess;
    throw new error;
  }finally{
    if(conn) await conn.end();
    return isSuccess;
  }
};*/

exports.registerUser = (user) => {
  const sql = 'INSERT INTO user(id,name,password,birth,gender,phoneNumber) VALUES (?,?,?,?,?,?)';
  const query = (conn)=>{
    const p = new Promise((resolve, reject)=> {
      conn
        .query(sql,user)
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

/*exports.transSmsMessage = (req,res) => {
 const phoneNumber = "010-9121-0769"

  request({
    method: 'POST',
    json: true,
    uri: `https://api-sens.ncloud.com/v1/sms/services/${process.env.SENS_SERVICEID}/messages`,
    headers: {
      'Content-Type': 'application/json',
      'X-NCP-auth-key': process.env.SENS_ACCESSKEYID,
      'X-NCP-service-secret': process.env.SENS_SERVICESECRET
    },
    body: {
      type: 'sms',
      from: process.env.SENS_SENDNUMBER,
      to: [`${phoneNumber}`],
      content: `WeGoing 인증번호 341353입니다.`
    }
  });
}*/


// exports.showUserProfile = (cid) => {
//   const query = (conn)=>{
//     const p = new Promise((resolve, reject)=> {
//       conn
//         .query('SELECT id,name,birth,gender,picture,intro,scope,point FROM user WHERE id = ?', [cid])
//         .then((result)=> {
//           conn.end()
//           resolve(result)
//         })
//         .catch((err) =>{
//           reject(err)})
//     })
//     return p;
//   }

//   const p = new Promise((resolve, reject) => {
//     dbcp.getConnection()
//       .then(query)
//       .then((result=> {
//         resolve(result)
//       }))
//       .catch((err) => {reject(err)})
//   })
//   return p;
// };

/*exports.addFriend = async (idList) => {//requester랑receiver가 db값과 둘다 같다면?  
  let conn;
  const sql = "INSERT INTO friends(requester,receiver,state) VALUES (?,?,?) "
  let isSuccess = true;
  try{
    conn = await dbcp.getConnection();
    await conn.query(sql,idList);
  }catch (error){
    isSuccess = !isSuccess;
    throw new error;
  }finally{
    if(conn) await conn.end();
    return isSuccess;
  }
}*/

exports.addFriend = (idList) => {
  const sql = 'INSERT INTO friends(requester,receiver,state) VALUES (?,?,?)';
  const query = (conn)=>{
    const p = new Promise((resolve, reject)=> {
      conn
        .query(sql,idList)
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

exports.acceptFriend = async (knotArr) => {//update 영향 받은 행이 없다면?
  let conn;
  const sql = "UPDATE friend SET state = 1 WHERE receiver = ? and requester = ? "
  let isSuccess = true;
  try{
    conn = await dbcp.getConnection();
    await conn.query(sql,knotArr,(err,result,field) => {
        if(err){
          throw new err;
        } else if(result.affectedRows === 0){
          isSuccess = !isSuccess;
        }
      });
  }catch (error){
    isSuccess = !isSuccess;
    throw new error;
  }finally{
    if(conn) await conn.end();
    return isSuccess;
  }
}

exports.refuseFriend = async (knotArr) => {
  let conn;
  const sql = "DELETE FROM friend WHERE receiver = ? and requester = ? "
  let isSuccess = true;
  try{
    conn = await dbcp.getConnection();
    await conn.query(sql,knotArr,(err,result,field) => {
        if(err){
          throw new err;
        } else if(result.affectedRows === 0){
          isSuccess = !isSuccess;
        }
      });
  }catch (error){
    isSuccess = !isSuccess;
    throw new error;
  }finally{
    if(conn) await conn.end();
    return isSuccess;
  }
}

exports.getChattingRooms = async () => {
  let conn;
  const sql = "SELECT id,roomName,master,password,type FROM chatroom "
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
}

exports.getDms = async (cid) => {
  let conn;
  const sql = "SELECT id,sender,message,time,isRead FROM dm WHERE receiver = ? "
  let dmResult;
  try{
    conn = await dbcp.getConnection();
    const [rows, fields] = await conn.query(sql,cid);
    dmResult = rows;
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return dmResult;
  }
}

exports.getPicture = async (cid) => {
  let conn;
  const sql = "SELECT picture FROM user WHERE id = ? "
  let pictureResult;
  try{
    conn = await dbcp.getConnection();
    await conn.query(sql , cid, (err,result,fields) => {
      if (err) throw err;
      console.log(result);
      pictureResult = result;
    });
 //   console.log(rows);
  }catch (error){
    throw error;
  }finally{
    if(conn) await conn.end();
    return pictureResult;
  }
} 