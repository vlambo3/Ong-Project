package com.alkemy.ong.utils.seeder;

public interface ISeederDataBase {
    
    void seedActivitiesTable(int amount);

    void seedCategoriesTable(int amount);

    void seedUsersTable();

    void seedRolesTable();

    /*
      TODO:Example pattern for next seeders:

      -> void seed+tableName+Table(int amount);
    */
}
