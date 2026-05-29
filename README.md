# Employee Management App

Full-stack Employee Management CRUD application with role-based authentication.

- **Backend:** Spring Boot REST API (Java 17, Spring Boot 3.4.5, Spring Security, Spring Data JPA, H2)
- **Frontend:** React 19 with Bootstrap 5, Axios, React Router

---

## Features

- JWT-based authentication — tokens are stored in `localStorage` and sent as `Authorization: Bearer` headers
- Role-based access control:
  - **MANAGER** — full CRUD (view, add, edit, delete employees)
  - **EMPLOYEE** — read-only (view list only, no add/edit/delete buttons)
- Login screen matching the app's existing Bootstrap card UI
- Navbar shows logged-in username, role badge, and a Logout button
- Unauthenticated users are redirected to `/login`

---

## Default Credentials

| Username   | Password      | Role     |
|------------|---------------|----------|
| `manager`  | `manager123`  | MANAGER  |
| `employee` | `employee123` | EMPLOYEE |

These users are seeded automatically on startup via `DataInitializer.java`.

---

## Running the App

### Backend

```bash
cd backend
JAVA_HOME=/path/to/java17 ./mvnw spring-boot:run
```

Starts on `http://localhost:8080`.

### Frontend

```bash
cd frontend
npm install
npm start
```

Starts on `http://localhost:3000`.

---

## API Endpoints

### Auth

| Method | Endpoint              | Access  | Description        |
|--------|-----------------------|---------|--------------------|
| POST   | `/api/v1/auth/login`  | Public  | Returns JWT token  |

**Request body:**
```json
{ "username": "manager", "password": "manager123" }
```

**Response:**
```json
{ "token": "<jwt>", "username": "manager", "role": "MANAGER" }
```

### Employees

All employee endpoints require a valid JWT in the `Authorization: Bearer <token>` header.

| Method | Endpoint                   | Role Required        | Description            |
|--------|----------------------------|----------------------|------------------------|
| GET    | `/api/v1/employees`        | MANAGER or EMPLOYEE  | List all employees     |
| GET    | `/api/v1/employees/{id}`   | MANAGER or EMPLOYEE  | Get employee by ID     |
| POST   | `/api/v1/employees`        | MANAGER only         | Create employee        |
| PUT    | `/api/v1/employees/{id}`   | MANAGER only         | Update employee        |
| DELETE | `/api/v1/employees/{id}`   | MANAGER only         | Delete employee        |

---

## Testing

### Manual — Browser

1. Start the backend and frontend.
2. Open `http://localhost:3000` — you should be redirected to `/login`.
3. Log in as `employee` / `employee123`:
   - The employee list loads.
   - No "Add Employee" button, no Update/Delete columns.
4. Log out, then log in as `manager` / `manager123`:
   - "Add Employee" button appears.
   - Update and Delete buttons appear in each row.
   - Creating, editing, and deleting employees all work.
5. Verify that navigating directly to `/add-employee` while logged in as `employee` redirects back to `/employees`.

### Manual — curl

**Login:**
```bash
curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"manager","password":"manager123"}' | jq .
```

**List employees (authenticated):**
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"manager","password":"manager123"}' | jq -r .token)

curl -s http://localhost:8080/api/v1/employees \
  -H "Authorization: Bearer $TOKEN" | jq .
```

**Verify EMPLOYEE cannot create (expect 403):**
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"employee","password":"employee123"}' | jq -r .token)

curl -s -o /dev/null -w "%{http_code}" \
  -X POST http://localhost:8080/api/v1/employees \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"test@test.com"}'
# Expected: 403
```

**Verify unauthenticated request is rejected (expect 401/403):**
```bash
curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/v1/employees
# Expected: 403
```

---

## Project Structure

```text
employee-management-app/
├── backend/
│   └── src/main/java/com/farzin/ems/
│       ├── config/
│       │   ├── DataInitializer.java       # Seeds default users on startup
│       │   └── SecurityConfig.java        # JWT filter chain, CORS, role rules
│       ├── controller/
│       │   ├── AuthController.java        # POST /api/v1/auth/login
│       │   └── EmployeeController.java    # CRUD endpoints
│       ├── dto/
│       │   ├── EmployeeDto.java
│       │   ├── JwtResponse.java
│       │   └── LoginRequest.java
│       ├── entity/
│       │   ├── Employee.java
│       │   ├── Role.java                  # MANAGER, EMPLOYEE
│       │   └── User.java
│       ├── repository/
│       │   ├── EmployeeRepository.java
│       │   └── UserRepository.java
│       ├── security/
│       │   ├── CustomUserDetailsService.java
│       │   ├── JwtAuthenticationFilter.java
│       │   └── JwtUtil.java
│       └── service/
│           ├── EmployeeService.java
│           └── EmployeeServiceImpl.java
└── frontend/
    └── src/
        ├── components/
        │   ├── EmployeeComponent.js       # Add/edit form (manager only)
        │   ├── FooterComponent.js
        │   ├── HeaderComponent.js         # Shows user, role badge, logout
        │   ├── ListEmployeeComponent.js   # Hides actions for non-managers
        │   └── LoginComponent.js          # Login form
        └── services/
            ├── AuthService.js             # login/logout/token helpers
            └── EmployeeService.js         # Axios calls with auth headers
```
