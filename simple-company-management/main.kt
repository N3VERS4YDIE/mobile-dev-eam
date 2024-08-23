val employeeRepository = CrudRepository<Employee>()
val clientRepository = CrudRepository<Client>()

fun main() {

        // TEST DATA

        val employees =
                listOf(
                        Employee(
                                "0000011111",
                                "Alice Smith",
                                "Female",
                                "alice@example.com",
                                5000.0,
                                Department.SALES,
                                2015,
                                EmployeePosition("Sales Manager", 2)
                        ),
                        Employee(
                                "0000022222",
                                "Bob Jones",
                                "Male",
                                "bob@example.com",
                                4000.0,
                                Department.OPERATIONS,
                                2018,
                                EmployeePosition("Operations Specialist", 3)
                        ),
                        Employee(
                                "0000033333",
                                "Carol Williams",
                                "Female",
                                "carol@example.com",
                                4500.0,
                                Department.HUMAN_RESOURCES,
                                2016,
                                EmployeePosition("HR Coordinator", 1),
                        )
                )
        employees.forEach { employeeRepository.save(it) }

        val clients =
                listOf(
                        Client(
                                "1111100000",
                                "David Brown",
                                "Male",
                                "david@example.com",
                                "123 Elm St",
                                "555-1234"
                        ),
                        Client(
                                "2222200000",
                                "Emma Johnson",
                                "Female",
                                "emma@example.com",
                                "456 Oak St",
                                "555-5678"
                        ),
                        Client(
                                "3333300000",
                                "Olivia Lee",
                                "Female",
                                "olivia@example.com",
                                "789 Pine St",
                                "555-8765"
                        )
                )
        clients.forEach { clientRepository.save(it) }

        val company =
                Company(
                        nit = "123456789",
                        name = "Evil Corp",
                        address = "123 Tech Lane",
                        employees = employeeRepository.readAll(),
                        clients = clientRepository.readAll()
                )

        // MAIN LOOP

        while (true) {
                println("\nSelect an option:")
                println("1. Get Total Payroll")
                println("2. Get Payroll by Department")
                println("3. Get Percentage of Clients by Gender")
                println("4. Get Number of Employees by Position")
                println("5. Get Employee with the Longest Tenure")
                println("6. Read All Employees")
                println("7. Read All Clients")
                println("8. Read Employee")
                println("9. Read Client")
                println("10. Save Employee")
                println("11. Save Client")
                println("12. Delete Employee")
                println("13. Delete Client")
                println("14. Exit\n")

                when (readLine()) {
                        "1" -> getTotalPayroll(employeeRepository.readAll())
                        "2" -> getPayrollByDepartment(employeeRepository.readAll())
                        "3" -> getClientGenderPercentages(clientRepository.readAll())
                        "4" -> getEmployeeCountByPosition(employeeRepository.readAll())
                        "5" -> getLongestServingEmployee(employeeRepository.readAll())
                        "6" -> readAllEntities(employeeRepository)
                        "7" -> readAllEntities(clientRepository)
                        "8" -> readEntity(employeeRepository)
                        "9" -> readEntity(clientRepository)
                        "10" -> saveEmployee()
                        "11" -> saveClient()
                        "12" -> deleteEntity(employeeRepository)
                        "13" -> deleteEntity(clientRepository)
                        "14" -> {
                                println("Exiting...")
                                return
                        }
                        else -> println("Invalid option. Please try again.")
                }
        }
}

// CLASSES

enum class Department {
        SALES,
        HUMAN_RESOURCES,
        MANAGEMENT,
        OPERATIONS
}

open class Entity(open val id: String)

data class Company(
        val nit: String,
        val name: String,
        val address: String,
        val employees: MutableList<Employee> = mutableListOf(),
        val clients: MutableList<Client> = mutableListOf()
) : Entity(nit)

open class Person(
        id: String,
        open val fullName: String,
        open val gender: String,
        open val email: String
) : Entity(id)

data class EmployeePosition(val name: String, val hierarchyLevel: Int)

data class Employee(
        override val id: String,
        override val fullName: String,
        override val gender: String,
        override val email: String,
        val salary: Double,
        val department: Department,
        val joiningYear: Int,
        val position: EmployeePosition,
        val subordinates: MutableList<Employee> = mutableListOf()
) : Person(id, fullName, gender, email)

data class Client(
        override val id: String,
        override val fullName: String,
        override val gender: String,
        override val email: String,
        val address: String,
        val phone: String
) : Person(id, fullName, gender, email)

class CrudRepository<T : Entity>(val storage: MutableMap<String, T> = mutableMapOf()) {

        fun readAll(): MutableList<T> {
                return storage.values.toMutableList()
        }

        fun read(id: String): T? {
                return storage[id]
        }

        fun save(entity: T) {
                val id = entity.id
                storage[id] = entity
        }

        fun delete(id: String): Boolean {
                return storage.remove(id) != null
        }
}

// FUNCTIONS

fun getTotalPayroll(employees: List<Employee>) {
        val totalPayroll = employees.sumOf { it.salary }
        println("Total Payroll: \$${totalPayroll}")
}

fun getPayrollByDepartment(employees: List<Employee>) {
        val payrollByDepartment =
                employees.groupBy { it.department }.mapValues { (_, employeesByDepartment) ->
                        employeesByDepartment.sumOf { it.salary }
                }

        println("Payroll by Department:")
        payrollByDepartment.forEach { (department, payroll) ->
                println("${department.name}: \$${payroll}")
        }
}

fun getClientGenderPercentages(clients: List<Client>) {
        val clientGenderCounts =
                clients.groupBy { it.gender.trim().uppercase() }.mapValues { (_, clientsByGender) ->
                        clientsByGender.size
                }
        val clientGenderPercentages =
                clientGenderCounts.mapValues { (_, count) -> count.toFloat() / clients.size * 100 }

        println("Client Gender Percentages:")
        clientGenderPercentages.forEach { (gender, percentage) -> println("$gender: $percentage%") }
}

fun getEmployeeCountByPosition(employees: List<Employee>) {
        val positionCounts =
                employees.groupBy { it.position.name }.mapValues { (_, employeesByPositionName) ->
                        employeesByPositionName.size
                }

        println("Number of Employees by Position:")
        positionCounts.forEach { (positionName, count) -> println("$positionName: $count") }
}

fun getLongestServingEmployee(employees: List<Employee>) {
        val longestServingEmployee = employees.minByOrNull { it.joiningYear }
        if (longestServingEmployee != null) {
                println("Employee with longest serving: ${longestServingEmployee.fullName}")
                println("Department: ${longestServingEmployee.department.name}")
        }
}

fun askForValue(message: String, validValues: List<String>? = null): String {
        while (true) {
                println("Enter ${message}:")

                val value = readLine()

                if (value != null && value.isNotBlank()) {
                        if (validValues == null || validValues.contains(value.trim().uppercase())) {
                                return value
                        } else {
                                println(
                                        "\nInvalid value. Please enter one of the following: ${validValues.joinToString(", ")}"
                                )
                        }
                }
        }
}

// CRUD FUNCTIONS

fun <T : Entity> readAllEntities(repository: CrudRepository<T>) {
        val entities = repository.readAll()
        if (entities.isNotEmpty()) {
                entities.forEach { println("${it}\n") }
        } else {
                println("No entities found.")
        }
}

fun <T : Entity> readEntity(repository: CrudRepository<T>) {
        val id = askForValue("ID")
        val entity = repository.read(id)
        if (entity != null) {
                println(entity)
        } else {
                println("Entity with ID ${id} not found.")
        }
}

fun saveEmployee() {
        try {
                val id = askForValue("Employee ID")
                val fullName = askForValue("Full Name")
                val gender = askForValue("Gender")
                val email = askForValue("Email")
                val salary = askForValue("Salary").toDouble()
                val departments = Department.values().map { it.name }
                val department =
                        Department.valueOf(
                                askForValue(
                                        "Department (${departments.joinToString(", ")})",
                                        departments
                                )
                        )
                val joiningYear = askForValue("Joining Year").toInt()
                val positionName = askForValue("Position Name")
                val hierarchyLevel = askForValue("Hierarchy Level").toInt()
                val subordinates =
                        askForValue("Subordinates (comma-separated IDs)")
                                .split(",")
                                .map { it.trim() }
                                .map { employeeRepository.read(id) }
                                .filterNotNull().toMutableList()

                val employee =
                        Employee(
                                id,
                                fullName,
                                gender,
                                email,
                                salary,
                                department,
                                joiningYear,
                                EmployeePosition(positionName, hierarchyLevel),
                                subordinates
                        )
                employeeRepository.save(employee)
                println("Employee saved successfully!")
        } catch (e: Exception) {
                println("Error saving employee: ${e.message}")
        }
}

fun saveClient() {
        try {
                val id = askForValue("Client ID")
                val fullName = askForValue("Full Name")
                val gender = askForValue("Gender")
                val email = askForValue("Email")
                val address = askForValue("Address")
                val phone = askForValue("Phone")

                val client = Client(id, fullName, gender, email, address, phone)

                clientRepository.save(client)

                println("Client saved successfully!")
        } catch (e: Exception) {
                println("Error saving client: ${e.message}")
        }
}

fun <T : Entity> deleteEntity(repository: CrudRepository<T>) {
        val id = askForValue("ID")
        if (repository.delete(id)) {
                println("Entity with ID ${id} deleted successfully.")
        } else {
                println("Entity with ID ${id} not found.")
        }
}
