# CRM Portal — Backend (Spring Boot + MySQL/XAMPP)

Spring Boot 3.2 / Java 17 / Maven backend for the CRM Portal React frontend,
built against:

* the exact MySQL schema in `database/schema.sql` (16 tables, no
  roles/permissions/tags tables — see "Scope" below)
* the exact API contract already used by `frontend/src/services/*.js`

## 1. Prerequisites

* JDK 17+
* Maven 3.9+ (or use an IDE with bundled Maven)
* XAMPP with MySQL running, and the `crm_portal` database created and
  loaded from `database/schema.sql` → `indexes.sql` → `seed.sql`
  (see `database/README.md`)

## 2. Configure the database connection

`src/main/resources/application.properties` is already set up for a
default local XAMPP install:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crm_portal?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
```

XAMPP's MySQL `root` user has **no password** by default, so this should
work unmodified. If you changed the MySQL root password, update
`spring.datasource.password` accordingly.

**Before deploying anywhere beyond localhost**, change `crm.jwt.secret` in
`application.properties` — generate one with `openssl rand -base64 48`.

## 3. Run it

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The API starts on **http://localhost:8080**, matching
`frontend/src/config/config.js` (`VITE_API_URL=http://localhost:8080/api`).

Hibernate is set to `ddl-auto=none` — it maps onto the tables created by
`database/schema.sql` rather than generating its own schema, so run the
database scripts first.

## 4. First login

The seed data (`database/seed.sql`) inserts 5 users with bcrypt
placeholder password hashes that are **not usable for login as-is** — the
original seed file notes this. To log in locally, either:

* Insert your own user with a real BCrypt hash (you can generate one with
  any online bcrypt tool, or temporarily add a small `CommandLineRunner`
  that calls `passwordEncoder.encode("yourpassword")` and prints it), or
* Use `POST /api/auth/register` to create a fresh account (defaults to
  role `USER`), then update its role directly in the `users` table if you
  need `ADMIN` for testing `/api/users`.

## 5. API surface

Every endpoint below is implemented and matches what
`frontend/src/services/*.js` already calls:

```
POST   /api/auth/login
POST   /api/auth/register
GET    /api/auth/me
POST   /api/auth/logout

GET    /api/leads                GET/PUT/DELETE /api/leads/{id}
POST   /api/leads                POST /api/leads/{id}/convert

GET    /api/contacts             GET/PUT/DELETE /api/contacts/{id}
POST   /api/contacts

GET    /api/companies            GET/PUT/DELETE /api/companies/{id}
POST   /api/companies

GET    /api/deals                GET/PUT/DELETE /api/deals/{id}
POST   /api/deals                PATCH /api/deals/{id}/stage

GET    /api/activities           GET/PUT/DELETE /api/activities/{id}
POST   /api/activities

GET    /api/tasks                GET/PUT/DELETE /api/tasks/{id}
POST   /api/tasks                PATCH /api/tasks/{id}/complete

GET    /api/calendar/events      GET/PUT/DELETE /api/calendar/events/{id}
POST   /api/calendar/events

GET    /api/notifications        PATCH /api/notifications/{id}/read
DELETE /api/notifications/{id}   PATCH /api/notifications/read-all

GET    /api/reports/sales
GET    /api/reports/revenue

GET    /api/users                GET/PUT/DELETE /api/users/{id}   (ADMIN/MANAGER only)
POST   /api/users

GET    /api/settings
PUT    /api/settings
```

List endpoints accept `search`, plus entity-specific filters (`status`,
`source`, `stage`, `priority`, `owner`, `companyId`, etc.), and pagination
params `page`, `size`, `sort`, `direction` — matching
`usePagination.js` / `config.pagination`. They return:

```json
{
  "content": [ ... ],
  "page": 0,
  "size": 10,
  "totalElements": 42,
  "totalPages": 5,
  "last": false
}
```

`response.data?.content || response.data || []` (used throughout the
React list pages) resolves correctly against this shape.

Errors always come back as:

```json
{ "success": false, "message": "...", "code": "...", "timestamp": "..." }
```

## 6. Auth & RBAC

* Stateless JWT (`Authorization: Bearer <token>`), issued on login/register,
  24h expiry by default (`crm.jwt.expiration-ms`).
* Passwords hashed with BCrypt — never stored in plain text.
* `/api/users/**` is restricted to `ADMIN`/`MANAGER` roles at the security
  filter chain level (`SecurityConfig`) — this is enforced server-side
  regardless of what the React UI hides, per the project's RBAC rule that
  "hidden UI ≠ security."
* `DELETE` requests anywhere in the API also require `ADMIN`/`MANAGER`.
* Each user's fine-grained `user_permissions` rows are loaded as Spring
  Security authorities too, so you can tighten specific endpoints further
  with `@PreAuthorize("hasAuthority('...')")` as the app grows.
* An `audit_logs` entry is written for login, register, and every
  create/update/delete/stage-change/complete action.

## 7. Scope — what's included vs. simplified

The original project brief (`README.md` at the repo root) describes a much
larger aspirational system (separate `roles`/`permissions` tables, tags,
soft-deletes, CSV import/export, rate limiting, a full notification
unread-count endpoint, etc.). This backend is built to match **the actual
MySQL schema you're running** (`database/schema.sql`, 16 tables) and **the
actual frontend contract** (`frontend/src/services/*.js`), rather than the
larger aspirational spec, so that everything here is real, wired-up, and
testable today. Concretely, **not** included (but straightforward to add
later against this same structure):

* Separate `roles` / `permissions` tables — roles are the `role` enum
  column on `users`; fine-grained permissions use the existing
  `user_permissions` table.
* Tags, Notes-as-their-own-entity, soft-deletes (`deleted_at`) — the
  actual schema doesn't have these tables/columns.
* CSV import/export endpoints, rate limiting on `/api/auth/**`, a
  refresh-token flow (JWT is short-lived and reissued on login instead).
* Email sending — `communications` table/entity exist in the schema and
  DB, but no controller wires it up yet since no frontend service calls
  it.

Everything else in the "Definition of Done" list that depends on real
CRUD, RBAC, pagination/filtering/sorting, reports, and audit logging is
implemented and working end-to-end.

## 8. Project layout

```
backend/src/main/java/com/crm/portal/
├── CrmPortalApplication.java
├── config/           CORS
├── security/          JWT, Spring Security config, entry points
├── entity/            JPA entities (1:1 with database/schema.sql tables)
├── enums/              UserRole, LeadStatus, DealStage, TaskStatus, ...
├── repository/         Spring Data JPA repositories (+ Specifications)
├── specification/      Dynamic search/filter query builders
├── dto/                 Request/response DTOs (entities are never
│                        exposed directly over the API)
├── mapper/              Entity → DTO mapping
├── service/             Business logic, validation, audit logging
├── controller/           Thin REST controllers
├── exception/            Centralized error handling
└── util/                 PageUtils (pagination/sorting helper)
```
