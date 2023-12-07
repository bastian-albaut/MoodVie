import persistance.dao.*;
import pw.mihou.dotenv.Dotenv;
import pw.mihou.dotenv.types.NormalDotenv;
//postgres://moodviedb_user:Q6Awtn1Z4hSGXpjDH9gQrxJIgo6Mr2eB@dpg-cloe15ap0o1s73f8su0g-a.frankfurt-postgres.render.com/moodviedb
public class Main {
    public static void main(String[] args) {;
        NormalDotenv dotenv = Dotenv.as();
        System.out.println(dotenv.get("JDBC_DATABASE_URL"));

        DaoFactory Factory = new PostGreFactory();
        UserDao UserDao = Factory.getUserDao();
        // Utilisez sqlUserDao pour les opérations avec la base de données SQL

        UserDao.addUser(new User(1, "Doe", "Test"));

        // Utilisez mongoUserDao pour les opérations avec MongoDB
    }
}

