const dbcp = require('./dbcp');

exports.addFeed = (feed) => {
  //console.log(feed);

  const sql = 'INSERT INTO feed(writer, picture, comment) VALUES (?,?,?)';
  const query = (conn) => {
    const p = new Promise((resolve, reject) => {
      conn
        .query(sql, [feed.writer, feed.picture, feed.comment])
        .then((result) => {
          conn.end()
          //console.log(result)
          resolve(result)
        })
        .catch((err) => { reject(err) })
    })
    return p;
  }

  const p = new Promise((resolve, reject) => {
    dbcp.getConnection()
      .then(query)
      .then((result => {
        resolve(result)
      }))
      .catch((err) => { reject(err) })
  })
  return p;
};

exports.showUserProfile = async (cid) => {
  let conn;
  const sql = "SELECT user.*, feed.* FROM user INNER JOIN feed ON feed.writer=user.id WHERE user.id = ?";
  let result;
  try {
    conn = await dbcp.getConnection();
    const [rows, fields] = await conn.query(sql, [cid]);
    result = rows;
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
};

exports.getFeeds = async (id) => {
  let conn;
  // 내꺼랑 친구꺼 피드만 보내기 (현재는 모든사용자 피드)
  // 필요한거 좋아요 갯수(likeCount), 내가 좋아요했는지 or 안했는지(like)
  const sql = '(SELECT feed.writer,feed.time, feed.picture, feed.comment, user.photo, user.id, user.name FROM feed ' +
    'JOIN user ON feed.writer = user.id ' +
    'JOIN friends ON feed.writer = friends.receiver ' +
    'WHERE friends.state = 1 AND friends.requester = ?) ' +
    'UNION ' +
    '(SELECT feed.writer,feed.time, feed.picture, feed.comment, user.photo, user.id, user.name FROM feed ' +
    'JOIN user ON feed.writer = user.id ' +
    'JOIN friends ON feed.writer = friends.requester ' +
    'WHERE friends.state = 1 AND friends.receiver = ?) ' +
    'UNION ' +
    '(SELECT feed.writer,feed.time, feed.picture, feed.comment, user.photo, user.id, user.name FROM feed ' +
    'JOIN user ON feed.writer = user.id ' +
    'WHERE feed.writer = ?) ' +
    'ORDER BY time DESC; ';

  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [id, id, id]);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}

exports.getFeed = async (id) => {
  let conn;
  const sql = "SELECT feed.* FROM feed WHERE writer= ? ";
  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, id);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}

exports.getLike = async (userId, feedId) => {
  let conn;
  const sql = "select `like`.id from `like` join feed on like.receiver = feed.writer where sender=? and feed.id=?";
  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [userId, feedId]);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}

exports.addLike = async (userId, feedId) => {
  let conn;
  const sql = "insert into `like` (sender, feedId, receiver) select ? as sender, ? as feedId, writer as receiver from feed where id=?";
  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [userId, feedId, feedId]);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}

exports.deleteLike = async (likeId) => {
  console.log(likeId);

  let conn;
  const sql = "delete from `like` where id=?";
  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, likeId);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}

exports.deleteFeed = async (userId, feedId) => {
  console.log("피드아이디:" + feedId + "\n현사용자: " + userId);

  let conn;
  const sql = "delete from feed where id = ? and writer = ?";
  let result;
  try {
    conn = await dbcp.getConnection();
    result = await conn.query(sql, [feedId, userId]);
  } catch (error) {
    throw error;
  } finally {
    if (conn) await conn.end();
    return result
  }
}