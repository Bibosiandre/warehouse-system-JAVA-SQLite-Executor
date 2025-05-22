
package com.example.warehouse.controller;

import com.example.warehouse.service.SqlExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class QueryController {

    @Autowired
    private SqlExecutorService sqlService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sql", "SELECT * FROM products;");
        return "query";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam String sql, Model model) {
        model.addAttribute("sql", sql);
        
        try {
            Map<String, Object> result = sqlService.execute(sql);
            
            // Для SELECT-запросов
            if (result.containsKey("columns")) {
                model.addAttribute("columns", (List<String>) result.get("columns"));
                model.addAttribute("rows", (List<Map<String, Object>>) result.get("rows"));
            } 
            // Для UPDATE/INSERT/DELETE
            else if (result.containsKey("updateCount")) {
                model.addAttribute("message", "Затронуто строк: " + result.get("updateCount"));
            }
            
        } catch (SQLException e) {
            model.addAttribute("error", "SQL-ошибка: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка: " + e.getMessage());
        }
        
        return "query";
    }
}