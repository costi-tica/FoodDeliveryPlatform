package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSchema {


    private Connection db;

    private static DatabaseSchema INSTANCE;

    private DatabaseSchema() {
        try {
            this.db = DatabaseConnection.getInstance();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static DatabaseSchema getInstance() {
        if (INSTANCE == null) INSTANCE = new DatabaseSchema();
        return INSTANCE;
    }

    public void config() {
        // ADDRESSES TABLE
        String createAddressesTable =
                """
                      create table if not exists `delivery_platform`.`addresses`(
                        id int not null primary key auto_increment,
                        city varchar(45) not null,
                        street varchar(45) not null,
                        number int default null
                      );
                """;
        try (PreparedStatement statement = db.prepareStatement(createAddressesTable)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // RESTAURANTS TABLE
        String createRestaurantsTable =
                """
                            CREATE TABLE IF NOT EXISTS `delivery_platform`.`restaurants` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(45) NOT NULL,
                          `address_id` INT NOT NULL,
                          PRIMARY KEY (`id`),
                          INDEX `restaurant_address_id_fk_idx` (`address_id` ASC) VISIBLE,
                          CONSTRAINT `restaurant_address_id_fk`
                            FOREIGN KEY (`address_id`)
                            REFERENCES `delivery_platform`.`addresses` (`id`)
                            ON DELETE NO ACTION
                            ON UPDATE NO ACTION);
                """;
        try (PreparedStatement statement = db.prepareStatement(createRestaurantsTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // CATEGORIES TABLE
        String createCategoriesTable =
        """
                CREATE TABLE IF NOT EXISTS `delivery_platform`.`categories` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `name` VARCHAR(45) NOT NULL,
              `restaurant_id` INT NOT NULL,
              PRIMARY KEY (`id`),
              INDEX `categories_res_id_fk_idx` (`restaurant_id` ASC) VISIBLE,
              CONSTRAINT `categories_res_id_fk`
                FOREIGN KEY (`restaurant_id`)
                REFERENCES `delivery_platform`.`restaurants` (`id`)
                ON DELETE CASCADE
                ON UPDATE CASCADE);
        """;
        try (PreparedStatement statement = db.prepareStatement(createCategoriesTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // PRODUCTS TABLE
        String createProductsTable =
        """
            CREATE TABLE IF NOT EXISTS `delivery_platform`.`products` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `name` VARCHAR(45) NOT NULL,
              `price` DECIMAL(7,2) NOT NULL,
              `quantity` INT NOT NULL,
              `category_id` int not null,
              PRIMARY KEY (`id`),
              constraint products_categ_fk foreign key (`category_id`) references categories(`id`) on delete cascade on update cascade
              );
        """;
        try (PreparedStatement statement = db.prepareStatement(createProductsTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // DRINKS TABLE
        String createProducts_IS_A_DrinkTable =
        """
              CREATE TABLE IF NOT EXISTS `delivery_platform`.`drinks` (
              `product_id` INT NOT NULL,
              `useless_field` VARCHAR(45) NULL,
              PRIMARY KEY (`product_id`),
              CONSTRAINT `drink_prod_id_fk`
                FOREIGN KEY (`product_id`)
                REFERENCES `delivery_platform`.`products` (`id`)
                ON DELETE CASCADE
                ON UPDATE CASCADE);
        """;
        try (PreparedStatement statement = db.prepareStatement(createProducts_IS_A_DrinkTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // DISHES TABLE
        String createProducts_IS_A_DishTable =
        """
                CREATE TABLE IF NOT EXISTS `delivery_platform`.`dishes` (
                `product_id` INT NOT NULL,
                `ingredients` VARCHAR(100) NULL,
                PRIMARY KEY (`product_id`),
                CONSTRAINT `dishes_prod_id_fk`
                  FOREIGN KEY (`product_id`)
                  REFERENCES `delivery_platform`.`products` (`id`)
                  ON DELETE CASCADE
                  ON UPDATE CASCADE);
        """;
        try (PreparedStatement statement = db.prepareStatement(createProducts_IS_A_DishTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // USER ROLES TABLE
        String createUserRolesTable =
        """
            CREATE TABLE IF NOT EXISTS `delivery_platform`.`user_roles` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `role_name` VARCHAR(45) NOT NULL,
            PRIMARY KEY (`id`),
            UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);
        """;
        try (PreparedStatement statement = db.prepareStatement(createUserRolesTable)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        String role1 = "insert into user_roles values(null, 'CLIENT');";
        String role2 = "insert into user_roles values(null, 'COURIER');";
        String role3 = "insert into user_roles values(null, 'RES_OWNER');";
        String role4 = "insert into user_roles values(null, 'APP_ADMIN');";
        try (PreparedStatement statement = db.prepareStatement(role1)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(role2)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(role3)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(role4)) { //try with resources
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }

        // USERS TABLE
        String createUsersTable =
        """
            CREATE TABLE IF NOT EXISTS `delivery_platform`.`users` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `name` VARCHAR(45) NOT NULL,
              `phone_number` VARCHAR(45) NOT NULL,
              `email` VARCHAR(45) NOT NULL,
              `password` VARCHAR(45) NOT NULL,
              `role` INT NOT NULL,
              PRIMARY KEY (`id`),
              UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
              CONSTRAINT users_role_fk FOREIGN KEY (`role`) REFERENCES user_roles(`id`)
              );
        """;
        try (PreparedStatement statement = db.prepareStatement(createUsersTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // CLIENTS TABLE
        String createUser_IS_A_ClientTable =
        """
              CREATE TABLE IF NOT EXISTS `delivery_platform`.`clients`(
                user_id int not null primary key,
                address_id int not null,
                constraint clients_user_id_fk foreign key (user_id) references users(id) on delete cascade,
                constraint clients_address_id_fk foreign key(address_id) references addresses(id)
              );
        """;
        try (PreparedStatement statement = db.prepareStatement(createUser_IS_A_ClientTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // COURIERS TABLE
        String createUser_IS_A_CourierTable =
        """
              CREATE TABLE IF NOT EXISTS `delivery_platform`.`couriers`(
                user_id int not null primary key,
                busy boolean default false,
                constraint couriers_user_id_fk foreign key (user_id) references users(id) on delete cascade
              );
        """;
        try (PreparedStatement statement = db.prepareStatement(createUser_IS_A_CourierTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // ADMIN RANKS TABLE
        String createAdminRanksTable =
        """
                CREATE TABLE IF NOT EXISTS `delivery_platform`.`admin_ranks` (
                `id` INT NOT NULL AUTO_INCREMENT,
                `rank` VARCHAR(45) NOT NULL UNIQUE,
                PRIMARY KEY (`id`));
        """;
        try (PreparedStatement statement = db.prepareStatement(createAdminRanksTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        String rank1 = "insert into admin_ranks values (null, 'LIEUTENANT');";
        String rank2 = "insert into admin_ranks values (null, 'CAPTAIN');";
        String rank3 = "insert into admin_ranks values (null, 'MAJOR');";
        String rank4 = "insert into admin_ranks values (null, 'COLONEL');";
        String rank5 = "insert into admin_ranks values (null, 'GENERAL');";
        try (PreparedStatement statement = db.prepareStatement(rank1)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(rank2)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(rank3)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(rank4)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }
        try (PreparedStatement statement = db.prepareStatement(rank5)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }


        // CREATE ADMINS TABLE
        String createAdminSTable =
          """
              CREATE TABLE IF NOT EXISTS `delivery_platform`.`admins` (
              `user_id` INT NOT NULL,
              `rank_id` INT NOT NULL,
              PRIMARY KEY (`user_id`),
              INDEX `admin_rank_id_fk_idx` (`rank_id` ASC) VISIBLE,
              CONSTRAINT `admin_user_id_fk`
                FOREIGN KEY (`user_id`)
                REFERENCES `delivery_platform`.`users` (`id`)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
              CONSTRAINT `admin_rank_id_fk`
                FOREIGN KEY (`rank_id`)
                REFERENCES `delivery_platform`.`admin_ranks` (`id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION);
        """;
        try (PreparedStatement statement = db.prepareStatement(createAdminSTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // RESTAURANT OWNER TABLE
        String createRestaurantOwnerSTable =
        """
              CREATE TABLE IF NOT EXISTS `delivery_platform`.`restaurant_owner` (
              `user_id` INT NOT NULL,
              `restaurant_id` INT NULL,
              PRIMARY KEY (`user_id`),
              INDEX `res_owner_res_id_fk_idx` (`restaurant_id` ASC) VISIBLE,
              CONSTRAINT `res_owner_user_id_fk`
                FOREIGN KEY (`user_id`)
                REFERENCES `delivery_platform`.`users` (`id`)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
              CONSTRAINT `res_owner_res_id_fk`
                FOREIGN KEY (`restaurant_id`)
                REFERENCES `delivery_platform`.`restaurants` (`id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION);
        """;
        try (PreparedStatement statement = db.prepareStatement(createRestaurantOwnerSTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // REVIEWS TABLE
        String createReviewsTable =
        """
                CREATE TABLE IF NOT EXISTS `delivery_platform`.`reviews` (
               `id` INT NOT NULL AUTO_INCREMENT,
               `message` VARCHAR(45) NOT NULL,
               `stars` INT NOT NULL,
               `client_id` INT NOT NULL,
               `restaurant_id` int not null references restaurants(`id`),
               `date` DATETIME NOT NULL,
               PRIMARY KEY (`id`),
               CONSTRAINT `reviews_client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `delivery_platform`.`clients` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
               CONSTRAINT `reviews_restaurant_id_fk` FOREIGN KEY (`restaurant_id`) REFERENCES `delivery_platform`.`restaurants` (`id`) on delete cascade on update cascade
               );
        """;
        try (PreparedStatement statement = db.prepareStatement(createReviewsTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // ORDER STATUS TABLE
        String createOrderStatusTable =
            """
               CREATE TABLE IF NOT EXISTS `delivery_platform`.`order_status` (
               `id` INT NOT NULL AUTO_INCREMENT,
               `status` VARCHAR(45) NOT NULL UNIQUE,
               PRIMARY KEY (`id`));
            """;
        try (PreparedStatement statement = db.prepareStatement(createOrderStatusTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        String status1 = "insert into order_status values (null, 'PLACED')";
        String status2 = "insert into order_status values (null, 'ON_DELIVERY')";
        String status3 = "insert into order_status values (null, 'COMPLETED')";
        try {
            PreparedStatement statement = db.prepareStatement(status1);
            statement.executeUpdate();
            statement = db.prepareStatement(status2);
            statement.executeUpdate();
            statement = db.prepareStatement(status3);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e) {
            if (!e.getMessage().contains("Duplicate entry"))
                e.printStackTrace();
        }

        // ORDERS TABLE
        String createOrderTable =
        """
             CREATE TABLE IF NOT EXISTS `delivery_platform`.`orders` (
             `id` INT NOT NULL AUTO_INCREMENT,
             `client_id` INT NOT NULL,
             `restaurant_id` INT NOT NULL,
             `courier_id` INT NULL DEFAULT NULL,
             `date` DATETIME NOT NULL,
             `status_id` INT NOT NULL,
             PRIMARY KEY (`id`),
             INDEX `order_client_id_fk_idx` (`client_id` ASC) VISIBLE,
             INDEX `order_res_id_fk_idx` (`restaurant_id` ASC) VISIBLE,
             INDEX `order_courier_id_fk_idx` (`courier_id` ASC) VISIBLE,
             INDEX `order_status_fk_idx` (`status_id` ASC) VISIBLE,
             CONSTRAINT `order_client_id_fk`
               FOREIGN KEY (`client_id`)
               REFERENCES `delivery_platform`.`clients` (`user_id`)
               ON DELETE CASCADE
               ON UPDATE CASCADE,
             CONSTRAINT `order_res_id_fk`
               FOREIGN KEY (`restaurant_id`)
               REFERENCES `delivery_platform`.`restaurants` (`id`)
               ON DELETE CASCADE
               ON UPDATE CASCADE,
             CONSTRAINT `order_courier_id_fk`
               FOREIGN KEY (`courier_id`)
               REFERENCES `delivery_platform`.`couriers` (`user_id`)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
             CONSTRAINT `order_status_fk`
               FOREIGN KEY (`status_id`)
               REFERENCES `delivery_platform`.`order_status` (`id`)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION);
        """;
        try (PreparedStatement statement = db.prepareStatement(createOrderTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // ORDER PRODUCTS TABLE
        String createOrderProductsTable =
        """
             CREATE TABLE IF NOT EXISTS `delivery_platform`.`order_products` (
             `order_id` INT NOT NULL,
             `product_id` INT NOT NULL,
             PRIMARY KEY (`order_id`, `product_id`),
             INDEX `order_prods_prod_id_fk_idx` (`product_id` ASC) VISIBLE,
             CONSTRAINT `order_prods_order_id_fk`
               FOREIGN KEY (`order_id`)
               REFERENCES `delivery_platform`.`orders` (`id`)
               ON DELETE CASCADE
               ON UPDATE CASCADE,
             CONSTRAINT `order_prods_prod_id_fk`
               FOREIGN KEY (`product_id`)
               REFERENCES `delivery_platform`.`products` (`id`)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION);
        """;
        try (PreparedStatement statement = db.prepareStatement(createOrderProductsTable)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
