package com.example.warehouse.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class SqlExecutorService {
    private final String url = "jdbc:sqlite:warehouse.db";

    public Map<String, Object> execute(String sql) throws SQLException {
        // 1. Проверка на опасные DDL-операции
        if (isDdlQuery(sql)) {
            throw new SQLException("DDL-операции запрещены");
        }

        // 2. Использование try-with-resources для автоматического закрытия ресурсов
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            Map<String, Object> result = new HashMap<>();
            boolean hasResultSet = stmt.execute(sql);

            if (hasResultSet) {
                // 3. Обработка SELECT-запросов
                try (ResultSet rs = stmt.getResultSet()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int cols = md.getColumnCount();

                    // 4. Использование LinkedHashMap для сохранения порядка колонок
                    List<String> columns = new ArrayList<>(cols);
                    for (int i = 1; i <= cols; i++) {
                        columns.add(md.getColumnLabel(i));
                    }

                    List<Map<String, Object>> rows = new ArrayList<>();
                    while (rs.next()) {
                        Map<String, Object> row = new LinkedHashMap<>();
                        for (String col : columns) {
                            // 5. Обработка NULL-значений
                            Object value = rs.getObject(col);
                            row.put(col, value != null ? value : "NULL");
                        }
                        rows.add(row);
                    }
                    
                    result.put("columns", columns);
                    result.put("rows", rows);
                }
            } else {
                // 6. Обработка DML-запросов
                int updateCount = stmt.getUpdateCount();
                result.put("updateCount", updateCount);
            }
            
            return result;
        }
    }

    // 7. Проверка на DDL-запросы
    private boolean isDdlQuery(String sql) {
        String cleanSql = sql.trim().toUpperCase();
        return cleanSql.startsWith("CREATE") ||
               cleanSql.startsWith("DROP") ||
               cleanSql.startsWith("ALTER") ||
               cleanSql.startsWith("TRUNCATE");
    }
}