var mariadb = require('mariadb');

module.exports = function () {
  var pool = mariadb.createPool({
    host: 'localhost',
    port: 3307,
    database: 'ryan',
    user: 'root',
    password: 'wjd133!@',
    connectionLimit:50,
  });

  return {
    getConnection: async function (){
      conn = await pool.getConnection();
      return conn;
    },
    release: async function(conn){
      if(conn) conn.end();
    }
  }
}();
