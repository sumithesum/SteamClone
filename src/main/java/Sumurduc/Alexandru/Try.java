package Sumurduc.Alexandru;

import org.springframework.jdbc.core.JdbcTemplate;

public class Try {
    public static void create(JdbcTemplate template){
        template.execute("CREATE TABLE IF NOT EXISTS Developer (\n" +
                "    developer_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    username VARCHAR(100) NOT NULL UNIQUE,\n" +
                "    email VARCHAR(255),\n" +
                "    phone VARCHAR(50),\n" +
                "    password VARCHAR(255) NOT NULL,\n" +
                "    bank FLOAT DEFAULT 0,\n" +
                "    studio_name VARCHAR(255),\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ") ENGINE=InnoDB;\n"
        );
        template.execute("CREATE TABLE IF NOT EXISTS Game (\n" +
                "    game_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    developer_id INT NOT NULL,\n" +
                "    title VARCHAR(255) NOT NULL,\n" +
                "    tags VARCHAR(255),\n" +
                "    description TEXT,\n" +
                "    price DECIMAL(10,2) DEFAULT 0.00,\n" +
                "    discount DECIMAL(5,2) DEFAULT 0.00,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    FOREIGN KEY (developer_id) REFERENCES Developer(developer_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB;\n");
        template.execute("CREATE TABLE IF NOT EXISTS Player (\n" +
                "    player_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    username VARCHAR(100) NOT NULL UNIQUE,\n" +
                "    email VARCHAR(255),\n" +
                "    phone VARCHAR(50),\n" +
                "    password VARCHAR(255) NOT NULL,\n" +
                "    bank FLOAT DEFAULT 0,\n" +
                "    private BOOL DEFAULT 0,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ") ENGINE=InnoDB;\n");

        template.execute("CREATE TABLE IF NOT EXISTS Friends (\n" +
                "    player_id INT NOT NULL,\n" +
                "    friend_id INT NOT NULL,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    PRIMARY KEY (player_id, friend_id),\n" +
                "    FOREIGN KEY (player_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (friend_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB;\n");

        template.execute("CREATE TABLE IF NOT EXISTS Comments (\n" +
                "    player_id INT NOT NULL,\n" +
                "    game_id INT NOT NULL,\n" +
                "    comment TEXT,\n" +
                "    vote TINYINT, -- de ex. -1 / 0 / 1 sau 1..5 în funcție de sistem\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    PRIMARY KEY (player_id, game_id),\n" +
                "    FOREIGN KEY (player_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (game_id) REFERENCES Game(game_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB;\n");

        template.execute("CREATE TABLE IF NOT EXISTS Library (\n" +
                "    player_id INT NOT NULL,\n" +
                "    game_id INT NOT NULL,\n" +
                "    hours_played DECIMAL(6,2) DEFAULT 0.00,\n" +
                "    acquired_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    PRIMARY KEY (player_id, game_id),\n" +
                "    FOREIGN KEY (player_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (game_id) REFERENCES Game(game_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB;\n");
        template.execute("CREATE TABLE IF NOT EXISTS Messages (\n" +
                "    message_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    sender_id INT NOT NULL,\n" +
                "    receiver_id INT NOT NULL,\n" +
                "    game_id INT NULL, -- opțional, dacă mesajul e legat de un joc\n" +
                "    message TEXT NOT NULL,\n" +
                "    sent_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    is_read BOOLEAN DEFAULT FALSE,\n" +
                "    FOREIGN KEY (sender_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (receiver_id) REFERENCES Player(player_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (game_id) REFERENCES Game(game_id)\n" +
                "        ON DELETE SET NULL\n" +
                "        ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB;\n");

    }

    public static void  drops(JdbcTemplate template)
    {
        for(int i = 0 ; i < 3 ; i ++){
            template.execute("DROP TABLE IF EXISTS Friends;");
            template.execute("DROP TABLE IF EXISTS Comments;");
            template.execute("DROP TABLE IF EXISTS Messages;");
            template.execute("DROP TABLE IF EXISTS Library;");
            template.execute("DROP TABLE IF EXISTS Game;");
            template.execute("DROP TABLE IF EXISTS Developer;");
            template.execute("DROP TABLE IF EXISTS Player;");
        }
    }
}
