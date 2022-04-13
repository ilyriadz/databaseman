/*
 * Copyright 2022 kiradja.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ilyriadz.databaseman;

/**
 *
 * @author sadaoui ilyes
 */
public class H2DatabaseClient extends H2Database
{

    @Override
    protected String jdbc() {
        return super.jdbc().concat("tcp://");
    }
 
    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ServiceLoader<DatabaseManager> loader = ServiceLoader.load(DatabaseManager.class);
        var lst = loader.stream()
                .map(e -> e.get())
                .toList();
        
        for (var l : lst)
            if (l instanceof H2DatabaseClient db)
            {
                db.connect("127.0.1.1/./yahalawa", "", "1");
                
                db.createTable("halawa", "fname varchar", "lname varchar");
                db.showTable("halawa");
            }
                
    }//*/
}
