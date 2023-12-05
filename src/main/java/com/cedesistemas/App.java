package com.cedesistemas;

import com.cedesistemas.factory.EmployeeFactory;
import com.cedesistemas.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        exercice1();
        exercice2();
        exercice3();
        exercice4();
        exercice5();
        
        System.out.println("\n6. Imprimir el menor salario. \n");
        int menorSalario = getMenorSalario();

        exercice7(menorSalario);
        exercice8();
        exercice9();
        exercice10();
        exercice11();
        exercice12();
        exercice13();
        exercice14();
    }

    private static void exercice14() {
        System.out.println("\n14. Crear un mapa basado en los datos, la clave debe ser el año de ingreso y el valor " +
                "debe ser la cantidad de personas que ingresaron en ese año en particular.\n");

        employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getId().substring(0,4)))
                .forEach((key, employees) -> {
                    System.out.println("En el año " + key + " ingresaron " + employees.size() + " personas ");
                });

    }

    private static void exercice13() {
        System.out.println("\n13. Crear un mapa basado en los datos, la clave debe ser el año de ingreso y el valor " +
                "debe ser la lista de todos los empleados que ingresaron en ese año en particular.\n");

        employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getId().substring(0,4)))
                .forEach((key, employees) -> {
                    System.out.println(key);
                    employees.forEach(System.out::println);
                });
    }

    private static void exercice12() {
        System.out.println("\n12. Listar todas las personas que trabajan con Robert Downey Jr.\n");
        employeeList.stream()
                .filter(employee ->
                        employee.getProjects()
                                .stream()
                                .anyMatch(project -> project.getProjectManager().equalsIgnoreCase("Robert Downey Jr"))
                )
                .forEach(System.out::println);
    }

    private static void exercice11() {
        System.out.println("\n11. Listar todos los proyectos en los cuales el Project Manager es Robert Downey Jr.\n");
        employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .distinct()
                .filter(project -> project.getProjectManager().equalsIgnoreCase("Robert Downey Jr"))
                .forEach(System.out::println);
    }

    private static void exercice10() {
        System.out.println("\n10. Contar todos los proyectos en los cuales el project manager es Robert Downey Jr. \n");
        long cantidadProyectosRobert = employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .distinct()
                .filter(project -> project.getProjectManager().equalsIgnoreCase("Robert Downey Jr"))
                .count();
        System.out.println(cantidadProyectosRobert);
    }

    private static void exercice9() {
        System.out.println("\n9. Contar el total de laptops asignados a los empleados. \n");
        long totalLaptops = employeeList.stream()
                .map(Employee::getTotalLaptopsAssigned)
                .reduce(0, Integer::sum);
        System.out.println(totalLaptops);
    }

    private static void exercice8() {
        System.out.println("\n8. Listar las personas trabajando en más de dos proyectos. \n");
        employeeList.stream()
                .filter(employee -> employee.getProjects().size() > 2)
                .forEach(System.out::println);
    }

    private static void exercice7(int menorSalario) {
        System.out.println("\n7. Imprimir la lista de los empleados con el menor salario. \n");

        employeeList.stream()
                .filter(employee -> employee.getSalary() == menorSalario)
                .forEach(System.out::println);
    }

    private static int getMenorSalario() {
        int menorSalario = employeeList.stream()
                .map(Employee::getSalary)
                .reduce(Integer::min)
                .get();
        System.out.println(menorSalario);
        return menorSalario;
    }

    private static void exercice5() {
        System.out.println("\n5. Imprimir los nombres de todos los empleados con el 3er salario mas alto. " +
                "Generalizar para el Nsimo salario más alto. \n");
        int tercerSalarioMasAlto = employeeList.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .get(2);

        employeeList.stream()
                .filter(employee -> employee.getSalary() == tercerSalarioMasAlto)
                .forEach(System.out::println);
    }

    private static void exercice4() {
        System.out.println("\n4. Ordenar los empleados basado en el primer nombre y salario \n");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparingInt(Employee::getSalary))
                .forEach(System.out::println);
    }

    private static void exercice3() {
        System.out.println("\n3. Listar todos los empleados quienes ingresaron en el año 2023 (El año se extrae del " +
                "ID del empleado, los primeros 4 caracteres \n");
        employeeList.stream()
                .filter(employee -> {
                    int ingreso = Integer.parseInt(employee.getId().substring(0, 4));
                    return ingreso == 2023;
                })
                .forEach(System.out::println);
    }

    private static void exercice2() {
        System.out.println("\n2. Imprimir el nombre completo de cualquier empleado cuyo primer nombre empiece por ‘A’\n");
        employeeList.stream()
                .filter(employee -> employee.getFirstName().substring(0,1).equalsIgnoreCase("A"))
                .forEach(System.out::println);
    }

    private static void exercice1() {
        System.out.println("\n1. Listar todos los proyectos distintos en orden desendente \n");
        employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .distinct()
                .sorted((p1, p2) -> p2.getName().compareToIgnoreCase(p1.getName()))
                .forEach(System.out::println);
    }
}
