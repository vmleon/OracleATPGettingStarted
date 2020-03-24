require("dotenv").config();
const oracledb = require("oracledb");
const logger = require("pino")();

function getBanner(connection) {
  return new Promise((resolve, reject) => {
    logger.debug("Querying database banner");
    const queryBanner = "SELECT banner FROM v$version";
    connection.execute(queryBanner, [], function(err, result) {
      if (err) {
        logger.error(err);
        reject(err);
      }
      const banner = result.rows[0][0];
      logger.debug("Closing connection");
      connection.close(err => err && logger.error(err));
      return resolve(banner);
    });
  });
}

function connectToDatabase(config) {
  logger.debug(`Connecting to ${config.connectString}`);
  oracledb.getConnection(config, async function(err, connection) {
    if (err) {
      logger.error(err);
      return;
    }

    const banner = await getBanner(connection);
    if (banner) {
      logger.info(`Connected to ${banner}`);
    } else {
      logger.error("No banner retrieved");
    }
  });
}

const dbconfig = {
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  connectString: process.env.DB_STRING
};

connectToDatabase(dbconfig);
