package pl.kayzone.dinnerapp.control;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;


public class BaseManager {

    private String connectionString;
    private MongoClient mongo;
    private Datastore datastore;
    private ZoneId timezone;

    private final Morphia morphia() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("pl.kayzone.dinnerapp.entity");
        return morphia;
    }

    public BaseManager() {
        setMongo(new MongoClient());
    }

    public BaseManager(String conn) {
        this();
        if (conn != null) {
            setConnectionString(conn);
            String dbname = conn.substring(conn.lastIndexOf("/") + 1, conn.length());
            System.out.println(dbname);
            this.datastore = morphia().createDatastore(getMongo(), dbname);
            datastore.ensureIndexes();
        } else {
            String dbname = "dinner";
            this.connectionString = "mongodb://localhost:27017/" + dbname;
            this.datastore = morphia().createDatastore(getMongo(), dbname);
            datastore.ensureIndexes();
        }
        this.timezone = ZoneId.of("Europe/Warsaw");

    }

    public void save(Object o) {
        datastore.save(o);
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public MongoClient getMongo() {
        return mongo;
    }

    public void setMongo(MongoClient mongo) {
        this.mongo = mongo;
    }
}
