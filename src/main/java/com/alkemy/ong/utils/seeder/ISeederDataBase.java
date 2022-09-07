package com.alkemy.ong.utils.seeder;

public interface ISeederDataBase {
    
    void seedActivitiesTable(int amount);

    void seedUsersTable(int amount, String role);

    void seedRoleTable(int amount);

    /*
      TODO:Example pattern for next seeders:

      -> void seed+tableName+Table(int amount);
    */
}
