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

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sadaoui ilyes
 */
public final class DbmanUtil 
{
    public static <T> JTable generateTable(DatabaseManager dbm, 
        String tableName, String others, Class<T> cls) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        var fields = Arrays.asList(cls.getDeclaredFields())
            .stream()
            .filter(e -> e.getAnnotation(TypeProperties.class) != null)
            .map(e -> e.getName())
            .toList();
        
        var columnNames = new String[fields.size()];
        fields.toArray(columnNames);
        
        var select = dbm.select(tableName, others, cls);
        
        Object[][] data = null;
        
        if (!select.isEmpty())
        {
            data = new Object[select.size()][];
            
            for (int i = 0; i < select.size(); i++)
            {
                Object[] record = new Object[columnNames.length];
                for (int j = 0; j < columnNames.length; j++) 
                {
                    Field f = cls.getDeclaredField(columnNames[j]);
                    f.setAccessible(true);
                    record[j] = f.get(select.get(i));
                }
                
                data[i] = record;
            }
        } // end if
            
        System.out.println(Arrays.toString(columnNames));
        
        JTable table = new JTable(data, columnNames);
        table.setShowGrid(true);
        
        return table;
    }
    
    public static <T> TableModel generateTableModel(DatabaseManager dbm, 
        String tableName, String others, Class<T> cls) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        var fields = Arrays.asList(cls.getDeclaredFields())
            .stream()
            .filter(e -> e.getAnnotation(TypeProperties.class) != null)
            .map(e -> e.getName())
            .toList();
        
        var columnNames = new String[fields.size()];
        fields.toArray(columnNames);
        
        var select = dbm.select(tableName, others, cls);
        
        Object[][] data = null;
        
        if (!select.isEmpty())
        {
            data = new Object[select.size()][];
            
            for (int i = 0; i < select.size(); i++)
            {
                Object[] record = new Object[columnNames.length];
                for (int j = 0; j < columnNames.length; j++) 
                {
                    Field f = cls.getDeclaredField(columnNames[j]);
                    f.setAccessible(true);
                    record[j] = f.get(select.get(i));
                }
                
                data[i] = record;
            }
        } // end if
        
        return new DefaultTableModel(data, columnNames);
    }
}
