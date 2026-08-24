# CRM Portal — Full-Stack CRM System

A production-ready SaaS-style Customer Relationship Management (CRM) portal built with:

* **Frontend:** React.js + JavaScript
* **Backend:** Spring Boot + Java
* **Database:** MySQL
* **Local Database Server:** XAMPP
* **API:** REST APIs
* **Authentication:** Secure session/JWT-based authentication
* **Authorization:** Role-Based Access Control (RBAC)
* **Build Tools:** npm for React, Maven for Spring Boot

---

## 1. Project Overview

This project is a complete full-stack CRM application for managing:

* Leads
* Contacts
* Companies
* Deals
* Sales pipelines
* Activities
* Tasks
* Calendar events
* Communications
* Notifications
* Reports
* Users
* Teams
* Roles
* Permissions
* CRM settings

The application follows this architecture:

```text
React.js Frontend
        |
        | HTTP / REST API
        v
Spring Boot Backend
        |
        | JPA / Hibernate
        v
MySQL Database
        |
        v
XAMPP MySQL Server
```

The frontend is responsible for presentation and user interaction.

The Spring Boot backend is responsible for:

* Authentication
* Authorization
* Business logic
* Validation
* CRUD operations
* Database access
* Security
* Audit logging
* Reporting
* API responses

MySQL stores the application's persistent data.

---

# 2. Recommended Project Structure

```text
crm-portal/
│
├── README.md
├── .gitignore
│
├── frontend/
│   │
│   ├── package.json
│   ├── package-lock.json
│   ├── vite.config.js
│   ├── index.html
│   │
│   ├── public/
│   │   ├── favicon.ico
│   │   └── images/
│   │
│   └── src/
│       │
│       ├── main.jsx
│       ├── App.jsx
│       ├── index.css
│       │
│       ├── assets/
│       │   ├── images/
│       │   ├── icons/
│       │   └── logos/
│       │
│       ├── components/
│       │   ├── common/
│       │   ├── layout/
│       │   ├── forms/
│       │   ├── tables/
│       │   ├── modals/
│       │   ├── charts/
│       │   ├── kanban/
│       │   └── feedback/
│       │
│       ├── pages/
│       │   ├── auth/
│       │   ├── dashboard/
│       │   ├── leads/
│       │   ├── contacts/
│       │   ├── companies/
│       │   ├── deals/
│       │   ├── activities/
│       │   ├── tasks/
│       │   ├── calendar/
│       │   ├── communications/
│       │   ├── reports/
│       │   ├── notifications/
│       │   ├── users/
│       │   ├── teams/
│       │   └── settings/
│       │
│       ├── services/
│       │   ├── api.js
│       │   ├── authService.js
│       │   ├── leadService.js
│       │   ├── contactService.js
│       │   ├── companyService.js
│       │   ├── dealService.js
│       │   ├── activityService.js
│       │   ├── taskService.js
│       │   ├── calendarService.js
│       │   ├── notificationService.js
│       │   ├── reportService.js
│       │   ├── userService.js
│       │   └── settingsService.js
│       │
│       ├── context/
│       │   ├── AuthContext.jsx
│       │   ├── ThemeContext.jsx
│       │   └── NotificationContext.jsx
│       │
│       ├── hooks/
│       │   ├── useAuth.js
│       │   ├── useFetch.js
│       │   ├── usePagination.js
│       │   ├── useDebounce.js
│       │   └── usePermissions.js
│       │
│       ├── routes/
│       │   ├── AppRoutes.jsx
│       │   ├── ProtectedRoute.jsx
│       │   └── PermissionRoute.jsx
│       │
│       ├── utils/
│       │   ├── constants.js
│       │   ├── validators.js
│       │   ├── formatters.js
│       │   ├── dateUtils.js
│       │   ├── exportUtils.js
│       │   └── storage.js
│       │
│       ├── config/
│       │   └── config.js
│       │
│       └── styles/
│           ├── variables.css
│           ├── components.css
│           └── responsive.css
│
├── backend/
│   │
│   ├── pom.xml
│   │
│   └── src/
│       ├── main/
│       │   │
│       │   ├── java/
│       │   │   └── com/
│       │       └── crm/
│       │           └── portal/
│       │               │
│       │               ├── CrmPortalApplication.java
│       │               │
│       │               ├── config/
│       │               ├── security/
│       │               ├── controller/
│       │               ├── service/
│       │               ├── repository/
│       │               ├── entity/
│       │               ├── dto/
│       │               ├── mapper/
│       │               ├── validation/
│       │               ├── exception/
│       │               ├── enums/
│       │               ├── specification/
│       │               └── util/
│       │
│       │   └── resources/
│       │       ├── application.properties
│       │       ├── application-dev.properties
│       │       ├── application-prod.properties
│       │       ├── db/
│       │       │   ├── migration/
│       │       │   └── seed/
│       │       └── static/
│       │
│       └── test/
│           └── java/
│
├── database/
│   ├── schema.sql
│   ├── seed.sql
│   ├── indexes.sql
│   └── README.md
│
└── docs/
    ├── API.md
    ├── DATABASE.md
    ├── AUTHENTICATION.md
    ├── RBAC.md
    └── DEPLOYMENT.md
```

---

# 3. Frontend

The frontend is a React.js application.

Recommended frontend setup:

```text
React.js
JavaScript
Vite
React Router
Axios
CSS / CSS Modules
Chart library
React Hook Form
Validation library
```

The frontend should communicate with Spring Boot exclusively through REST APIs.

It should not connect directly to MySQL.

---

# 4. frontend/package.json

This file defines:

* Project name
* React dependencies
* Development dependencies
* Build scripts
* Start scripts

Example responsibilities:

```text
npm install
npm run dev
npm run build
npm run preview
```

The frontend should contain no database credentials.

---

# 5. frontend/src/main.jsx

This is the React application entry point.

Responsibilities:

* Create the React root
* Load global CSS
* Mount the main application
* Initialize providers

Typical provider hierarchy:

```text
BrowserRouter
    |
    ├── AuthProvider
    |
    ├── ThemeProvider
    |
    └── NotificationProvider
            |
            └── App
```

---

# 6. frontend/src/App.jsx

This is the main React application component.

Responsibilities:

* Application routing
* Global layout
* Authentication-aware rendering
* Global notifications
* Global modals if required

It should not contain all business logic.

Business logic belongs in services/hooks.

---

# 7. frontend/src/components

Reusable UI components are stored here.

## components/common

Contains reusable elements such as:

```text
Button
Input
Select
Textarea
Checkbox
Radio
Badge
Avatar
Spinner
Loader
Tooltip
Dropdown
Tabs
Breadcrumb
Pagination
```

These components should be reusable throughout the application.

---

# 8. components/layout

Contains the main application shell.

Typical files:

```text
AppLayout.jsx
Sidebar.jsx
TopNavbar.jsx
MobileSidebar.jsx
PageHeader.jsx
Breadcrumbs.jsx
UserMenu.jsx
GlobalSearch.jsx
QuickCreate.jsx
```

The layout provides:

```text
------------------------------------------------
| Sidebar | Top Navigation                     |
|         |------------------------------------|
|         | Page Header                        |
|         |                                    |
|         | Main Content                       |
|         |                                    |
------------------------------------------------
```

The sidebar supports:

* Expanded mode
* Collapsed mode
* Mobile drawer
* Active route highlighting

---

# 9. Sidebar Modules

The sidebar contains:

```text
Dashboard
Leads
Contacts
Companies
Deals
Activities
Tasks
Calendar
Communications
Reports
Notifications
Users & Teams
Settings
```

Menu visibility can depend on permissions.

Important:

Frontend visibility is only a UX feature.

Actual authorization must always be enforced by Spring Boot.

---

# 10. components/tables

Reusable table functionality should be centralized.

Typical files:

```text
DataTable.jsx
TableHeader.jsx
TableRow.jsx
TablePagination.jsx
ColumnSelector.jsx
BulkActions.jsx
TableFilters.jsx
```

Tables support:

* Search
* Sorting
* Pagination
* Filtering
* Row actions
* Bulk selection
* Column visibility
* Export
* Empty state
* Loading state
* Error state

---

# 11. components/forms

Reusable form components include:

```text
LeadForm
ContactForm
CompanyForm
DealForm
TaskForm
ActivityForm
CalendarEventForm
UserForm
```

Every form should support:

* Client-side validation
* Server-side validation response
* Required fields
* Error messages
* Loading state
* Disabled submit
* Cancel action
* Success toast
* Duplicate submission prevention

---

# 12. components/charts

Dashboard and reports charts belong here.

Examples:

```text
PipelineChart.jsx
RevenueChart.jsx
LeadSourceChart.jsx
LeadStatusChart.jsx
DealStageChart.jsx
SalesPerformanceChart.jsx
ActivityTrendChart.jsx
```

Charts receive data through props and should not directly call APIs.

---

# 13. frontend/src/pages

Pages represent complete application screens.

Example:

```text
pages/
├── auth/
├── dashboard/
├── leads/
├── contacts/
├── companies/
├── deals/
├── activities/
├── tasks/
├── calendar/
├── communications/
├── reports/
├── notifications/
├── users/
├── teams/
└── settings/
```

---

# 14. pages/auth

Authentication screens:

```text
Login.jsx
ForgotPassword.jsx
ResetPassword.jsx
```

Login handles:

* Email
* Password
* Validation
* Authentication request
* Error messages
* Redirect after successful login

Forgot password sends a password reset request.

Reset password accepts a secure reset token and allows a new password.

---

# 15. pages/dashboard

The dashboard provides the CRM overview.

KPIs:

```text
Total Leads
New Leads
Total Contacts
Active Deals
Pipeline Value
Won Deals
Lost Deals
Tasks Due
Conversion Rate
```

Dashboard sections include:

```text
Sales Pipeline
Revenue Over Time
Leads by Source
Leads by Status
Deals by Stage
Sales Rep Performance
Activity Trends
Recent Leads
Recent Contacts
Recent Deals
Upcoming Tasks
Upcoming Meetings
Recent Activities
Notifications
```

Dashboard data comes from backend reporting APIs.

---

# 16. pages/leads

Lead pages include:

```text
LeadList.jsx
LeadDetails.jsx
CreateLead.jsx
EditLead.jsx
```

Lead fields:

```text
firstName
lastName
email
phone
company
jobTitle
source
status
rating
owner
industry
website
notes
tags
createdAt
updatedAt
```

Lead status:

```text
New
Contacted
Qualified
Unqualified
Converted
Lost
```

Lead source:

```text
Website
Referral
Advertisement
Social Media
Email
Phone
Event
Other
```

Supported functionality:

* CRUD
* Search
* Filter
* Sort
* Pagination
* Bulk operations
* Owner assignment
* Tags
* Import
* Export
* Convert lead
* Timeline
* Notes
* Tasks
* Activities
* Related deals
* Communications

---

# 17. pages/contacts

Contact pages:

```text
ContactList.jsx
ContactDetails.jsx
CreateContact.jsx
EditContact.jsx
```

Contact data:

```text
firstName
lastName
email
phone
alternatePhone
company
jobTitle
department
owner
address
city
state
country
postalCode
tags
notes
createdAt
updatedAt
```

Features:

* CRUD
* Search
* Filters
* Sorting
* Pagination
* Owner assignment
* Tags
* Import/export
* Timeline
* Related company
* Related deals
* Activities

---

# 18. pages/companies

Company pages:

```text
CompanyList.jsx
CompanyDetails.jsx
CreateCompany.jsx
EditCompany.jsx
```

Company fields:

```text
companyName
website
industry
companySize
phone
email
address
city
state
country
postalCode
owner
annualRevenue
status
notes
```

Details show:

```text
Company information
Contacts
Deals
Activities
Notes
Timeline
```

---

# 19. pages/deals

Deal pages include:

```text
DealList.jsx
DealDetails.jsx
CreateDeal.jsx
EditDeal.jsx
Pipeline.jsx
```

Default stages:

```text
1. Prospecting
2. Qualification
3. Proposal
4. Negotiation
5. Closed Won
6. Closed Lost
```

Pipeline screen uses Kanban columns.

Example:

```text
Prospecting     Qualification     Proposal
------------------------------------------------
Deal A          Deal C             Deal F
Deal B          Deal D             Deal G

Negotiation     Closed Won         Closed Lost
------------------------------------------------
Deal E          Deal H             Deal I
```

Drag-and-drop updates the deal stage through the backend API.

---

# 20. pages/activities

Activities support:

```text
Calls
Meetings
Emails
Notes
Follow-ups
```

Fields:

```text
subject
type
description
relatedLead
relatedContact
relatedCompany
relatedDeal
assignedUser
dateTime
status
priority
```

Activities appear on related record timelines.

---

# 21. pages/tasks

Task fields:

```text
title
description
dueDate
priority
status
assignedUser
relatedRecord
createdAt
```

Statuses:

```text
Todo
In Progress
Completed
Cancelled
```

Priorities:

```text
Low
Medium
High
Urgent
```

Features:

* Create
* Edit
* Delete
* Complete
* Assign
* Search
* Filter
* Due-date filtering
* Priority filtering

---

# 22. pages/calendar

Calendar supports:

```text
Day
Week
Month
```

Calendar events can represent:

```text
Meetings
Calls
Tasks
Follow-ups
```

Events support:

* Create
* Edit
* Delete
* Attendees
* Date/time
* Related CRM records
* Status
* Description

---

# 23. pages/communications

Communications contains:

```text
Email history
Call history
Messages
Notes
Communication timeline
```

Email sending should never be implemented directly inside React.

React calls:

```text
POST /api/communications/email
```

Spring Boot calls the configured email service.

If no provider is configured, a local/mock implementation stores the communication as history.

---

# 24. pages/notifications

Notification center supports:

```text
Unread notifications
Read notifications
Mark as read
Mark all as read
Unread count
```

Notification types include:

```text
New lead assigned
Deal update
Task reminder
Meeting reminder
Mention
System notification
```

---

# 25. pages/reports

Reports contain:

```text
Sales Reports
Lead Reports
Activity Reports
```

Sales reports:

```text
Revenue
Pipeline value
Won deals
Lost deals
Conversion rate
Average deal size
Sales performance
```

Lead reports:

```text
Leads by source
Leads by status
Lead conversion
Lead trends
```

Activity reports:

```text
Calls
Meetings
Emails
Completed tasks
```

Reports support:

* Date ranges
* Filters
* Charts
* Tables
* Export

---

# 26. pages/users and teams

Administrative screens:

```text
UsersList.jsx
UserDetails.jsx
CreateUser.jsx
EditUser.jsx
TeamsList.jsx
TeamDetails.jsx
```

User fields:

```text
name
email
phone
avatar
role
team
status
createdAt
lastLogin
```

Admin features:

* Create user
* Edit user
* Disable user
* Delete where appropriate
* Reset password
* Assign role
* Assign team
* Manage status

---

# 27. pages/settings

Settings contain:

```text
General
Profile
Security
Notifications
CRM Configuration
```

General:

```text
Company name
Logo
Contact information
Timezone
Currency
Date format
```

Profile:

```text
Name
Email
Phone
Avatar
Password
```

Security:

```text
Password management
Sessions
Login/security settings
```

Notifications:

```text
Email notifications
Task reminders
Deal notifications
```

CRM configuration:

```text
Lead statuses
Lead sources
Deal stages
Custom fields
Tags
```

---

# 28. frontend/src/services

This directory contains API communication.

The UI should not directly construct Axios/fetch requests everywhere.

Instead:

```text
Page
 ↓
Service
 ↓
API
 ↓
Spring Boot Controller
```

Example:

```text
leadService.js
```

Responsibilities:

```text
getLeads()
getLeadById()
createLead()
updateLead()
deleteLead()
convertLead()
searchLeads()
exportLeads()
```

The same pattern is used for contacts, companies, deals, tasks, etc.

---

# 29. frontend/src/services/api.js

Central API client.

Responsibilities:

* Base API URL
* HTTP requests
* Authentication handling
* Common headers
* Error handling
* Response handling

Example base URL:

```text
http://localhost:8080/api
```

This should be configurable through environment variables.

---

# 30. frontend/src/context/AuthContext.jsx

Global authentication state.

Stores information such as:

```text
currentUser
isAuthenticated
roles
permissions
loading
```

Provides functions:

```text
login()
logout()
refreshUser()
```

---

# 31. ThemeContext.jsx

Controls:

```text
Light mode
Dark mode
System mode
```

Theme preference can be persisted locally.

---

# 32. NotificationContext.jsx

Provides global toast/notification functionality.

Examples:

```text
Lead created successfully
Contact updated successfully
Deal deleted successfully
Unable to save changes
```

---

# 33. frontend/src/routes

Contains route protection.

```text
AppRoutes.jsx
ProtectedRoute.jsx
PermissionRoute.jsx
```

ProtectedRoute ensures unauthenticated users cannot access CRM pages.

PermissionRoute checks the user's permissions.

However, backend authorization remains mandatory.

---

# 34. frontend/src/hooks

Reusable React hooks.

Examples:

```text
useAuth()
useFetch()
usePagination()
useDebounce()
usePermissions()
```

These prevent duplicated React logic.

---

# 35. frontend/src/utils

Utility functions.

Examples:

```text
validators.js
formatters.js
dateUtils.js
exportUtils.js
storage.js
constants.js
```

Examples of formatting:

```text
formatCurrency()
formatDate()
formatPhone()
formatName()
```

---

# 36. Backend

The backend uses:

```text
Java
Spring Boot
Spring Web
Spring Data JPA
Hibernate
Spring Security
MySQL Driver
Bean Validation
Maven
```

The backend follows:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
MySQL
```

---

# 37. backend/pom.xml

Maven dependency configuration.

It contains dependencies for:

```text
Spring Boot
Spring Web
Spring Security
Spring Data JPA
MySQL
Validation
JWT/session security
Testing
```

It also defines:

```text
Java version
Spring Boot version
Build plugins
```

---

# 38. CrmPortalApplication.java

Main Spring Boot entry point.

Responsibilities:

* Start Spring Boot
* Component scanning
* Application initialization

Example package:

```text
com.crm.portal
```

---

# 39. backend/config

Application configuration.

Typical classes:

```text
CorsConfig.java
DatabaseConfig.java
JacksonConfig.java
WebConfig.java
AuditConfig.java
```

Responsibilities include:

* CORS
* JSON serialization
* Application beans
* Security-related configuration

---

# 40. backend/security

Security implementation.

Typical files:

```text
SecurityConfig.java
AuthenticationService.java
AuthorizationService.java
PasswordService.java
JwtService.java
SecurityUserDetailsService.java
AuthenticationFilter.java
```

Depending on the selected authentication approach, session authentication or JWT authentication is used consistently.

Security handles:

* Login
* Logout
* Password hashing
* Protected endpoints
* Authentication
* Authorization
* Roles
* Permissions
* Session/token validation

---

# 41. backend/controller

REST API endpoints.

Example:

```text
AuthController.java
UserController.java
LeadController.java
ContactController.java
CompanyController.java
DealController.java
ActivityController.java
TaskController.java
CalendarController.java
NotificationController.java
ReportController.java
SettingsController.java
```

Controllers should be thin.

They receive HTTP requests and delegate business logic to services.

---

# 42. backend/service

Business logic lives here.

Example:

```text
AuthService.java
UserService.java
LeadService.java
ContactService.java
CompanyService.java
DealService.java
ActivityService.java
TaskService.java
CalendarService.java
NotificationService.java
ReportService.java
SettingsService.java
AuditLogService.java
```

Example:

```text
LeadController
      ↓
LeadService
      ↓
LeadRepository
```

The service layer handles:

* Business rules
* Validation orchestration
* Permission checks
* Entity relationships
* Notifications
* Audit logging
* Transactions

---

# 43. backend/repository

Spring Data JPA repositories.

Examples:

```text
UserRepository.java
RoleRepository.java
PermissionRepository.java
LeadRepository.java
ContactRepository.java
CompanyRepository.java
DealRepository.java
ActivityRepository.java
TaskRepository.java
CalendarEventRepository.java
NotificationRepository.java
AuditLogRepository.java
```

Repositories handle database operations.

---

# 44. backend/entity

JPA database entities.

Minimum entities:

```text
User.java
Role.java
Permission.java
Team.java

Lead.java
Contact.java
Company.java
Deal.java
DealStage.java

Activity.java
Task.java
CalendarEvent.java

Notification.java
Note.java
Tag.java

LeadTag.java
ContactTag.java
CompanyTag.java
DealTag.java

AuditLog.java
```

These classes map Java objects to MySQL tables.

---

# 45. User Entity

User contains:

```text
id
name
email
passwordHash
phone
avatar
role
team
status
createdAt
updatedAt
lastLogin
deletedAt
```

Email should have an appropriate unique constraint.

Passwords must never be stored in plain text.

---

# 46. Role Entity

Default roles:

```text
Super Admin
Admin
Manager
Sales Representative
Support Agent
Viewer
```

Roles are associated with permissions.

---

# 47. Permission Entity

Permissions include:

```text
view
create
edit
delete
export
manage users
manage settings
```

Permissions are evaluated on the backend.

---

# 48. Team Entity

Teams allow users to be grouped.

Example:

```text
Sales Team
Support Team
Management Team
```

Users can be assigned to teams.

---

# 49. Lead Entity

Lead database fields include:

```text
id
firstName
lastName
email
phone
company
jobTitle
source
status
rating
owner
industry
website
notes
createdAt
updatedAt
deletedAt
```

Relationships include:

```text
Lead → User(owner)
Lead → Tags
Lead → Activities
Lead → Tasks
Lead → Notes
Lead → Deals
```

---

# 50. Contact Entity

Fields:

```text
id
firstName
lastName
email
phone
alternatePhone
company
jobTitle
department
owner
address
city
state
country
postalCode
notes
createdAt
updatedAt
deletedAt
```

Relationships:

```text
Contact → Company
Contact → User
Contact → Deals
Contact → Activities
Contact → Tags
```

---

# 51. Company Entity

Fields:

```text
id
companyName
website
industry
companySize
phone
email
address
city
state
country
postalCode
owner
annualRevenue
status
notes
createdAt
updatedAt
deletedAt
```

Relationships:

```text
Company → Contacts
Company → Deals
Company → Activities
Company → User
Company → Tags
```

---

# 52. Deal Entity

Fields:

```text
id
dealName
company
contact
value
currency
stage
probability
expectedCloseDate
owner
source
description
createdAt
updatedAt
deletedAt
```

Relationships:

```text
Deal → Company
Deal → Contact
Deal → User
Deal → DealStage
Deal → Tags
Deal → Activities
```

---

# 53. DealStage Entity

Default records:

```text
Prospecting
Qualification
Proposal
Negotiation
Closed Won
Closed Lost
```

Each stage can contain:

```text
id
name
position
probability
active
createdAt
updatedAt
```

---

# 54. Activity Entity

Fields:

```text
id
subject
type
description
lead
contact
company
deal
assignedUser
dateTime
status
priority
createdAt
updatedAt
```

Activity types:

```text
CALL
MEETING
EMAIL
NOTE
FOLLOW_UP
```

---

# 55. Task Entity

Fields:

```text
id
title
description
dueDate
priority
status
assignedUser
relatedLead
relatedContact
relatedCompany
relatedDeal
createdAt
updatedAt
```

---

# 56. CalendarEvent Entity

Stores calendar events.

Fields include:

```text
id
title
description
startDateTime
endDateTime
eventType
status
location
assignedUser
lead
contact
company
deal
createdAt
updatedAt
```

Attendees can be represented through a separate relationship/table if required.

---

# 57. Notification Entity

Fields:

```text
id
user
type
title
message
read
createdAt
readAt
```

Notifications are user-specific.

---

# 58. Note Entity

Notes can be associated with:

```text
Lead
Contact
Company
Deal
```

Fields:

```text
id
content
createdBy
createdAt
updatedAt
```

---

# 59. Tag Entities

Tags provide flexible categorization.

Main table:

```text
tags
```

Relationship tables:

```text
lead_tags
contact_tags
company_tags
deal_tags
```

This creates many-to-many relationships.

---

# 60. AuditLog Entity

Audit logs track important system operations.

Examples:

```text
LOGIN
LOGOUT
CREATE_USER
UPDATE_USER
DELETE_USER
ROLE_CHANGED
PASSWORD_CHANGED
CREATE_LEAD
UPDATE_DEAL
DELETE_RECORD
SETTINGS_CHANGED
```

Fields:

```text
id
user
action
entityType
entityId
description
ipAddress
userAgent
createdAt
```

---

# 61. backend/dto

DTOs prevent exposing internal database entities directly through APIs.

Examples:

```text
LoginRequest.java
LoginResponse.java

UserDto.java
CreateUserRequest.java
UpdateUserRequest.java

LeadDto.java
CreateLeadRequest.java
UpdateLeadRequest.java

ContactDto.java
CompanyDto.java
DealDto.java
TaskDto.java
ActivityDto.java
NotificationDto.java
```

DTOs define exactly what the API accepts and returns.

---

# 62. backend/mapper

Maps between:

```text
Entity ↔ DTO
```

Examples:

```text
LeadMapper.java
ContactMapper.java
CompanyMapper.java
DealMapper.java
UserMapper.java
```

This keeps controllers and services cleaner.

---

# 63. backend/validation

Validation classes and custom validators.

Examples:

```text
PasswordValidator.java
EmailValidator.java
DateRangeValidator.java
DealValidator.java
```

Spring Bean Validation should handle standard validation:

```text
@NotNull
@NotBlank
@Email
@Size
@Positive
```

Frontend validation improves UX, but backend validation remains authoritative.

---

# 64. backend/exception

Centralized exception handling.

Typical files:

```text
GlobalExceptionHandler.java
ResourceNotFoundException.java
UnauthorizedException.java
ForbiddenException.java
ValidationException.java
DuplicateResourceException.java
```

The API should return consistent errors.

Example:

```json
{
  "success": false,
  "message": "Lead not found",
  "code": "RESOURCE_NOT_FOUND",
  "timestamp": "..."
}
```

---

# 65. backend/enums

Centralized application enumerations.

Examples:

```text
RoleType
UserStatus
LeadStatus
LeadSource
DealStatus
ActivityType
TaskStatus
TaskPriority
NotificationType
```

This prevents inconsistent string values throughout the backend.

---

# 66. backend/specification

Contains dynamic database filtering logic.

Useful for:

```text
Search
Filtering
Sorting
Date ranges
Owner filtering
Status filtering
Stage filtering
```

For example:

```text
LeadSpecification
DealSpecification
ContactSpecification
CompanySpecification
TaskSpecification
```

Spring Data Specifications can construct dynamic queries efficiently.

---

# 67. API Structure

The REST API uses:

```text
/api/auth
/api/users
/api/leads
/api/contacts
/api/companies
/api/deals
/api/activities
/api/tasks
/api/calendar
/api/notifications
/api/reports
/api/settings
```

---

# 68. Authentication APIs

```text
POST   /api/auth/login
POST   /api/auth/logout
POST   /api/auth/forgot-password
POST   /api/auth/reset-password
GET    /api/auth/me
GET    /api/auth/sessions
DELETE /api/auth/sessions/{id}
```

---

# 69. Leads API

```text
GET    /api/leads
GET    /api/leads/{id}
POST   /api/leads
PUT    /api/leads/{id}
DELETE /api/leads/{id}
POST   /api/leads/{id}/convert
POST   /api/leads/import
GET    /api/leads/export
```

Query parameters can include:

```text
search
status
source
owner
rating
page
size
sort
direction
```

---

# 70. Contacts API

```text
GET    /api/contacts
GET    /api/contacts/{id}
POST   /api/contacts
PUT    /api/contacts/{id}
DELETE /api/contacts/{id}
POST   /api/contacts/import
GET    /api/contacts/export
```

---

# 71. Companies API

```text
GET    /api/companies
GET    /api/companies/{id}
POST   /api/companies
PUT    /api/companies/{id}
DELETE /api/companies/{id}
GET    /api/companies/export
```

---

# 72. Deals API

```text
GET    /api/deals
GET    /api/deals/{id}
POST   /api/deals
PUT    /api/deals/{id}
DELETE /api/deals/{id}
PUT    /api/deals/{id}/stage
GET    /api/deals/pipeline
GET    /api/deals/forecast
```

---

# 73. Tasks API

```text
GET    /api/tasks
GET    /api/tasks/{id}
POST   /api/tasks
PUT    /api/tasks/{id}
DELETE /api/tasks/{id}
PATCH  /api/tasks/{id}/complete
```

---

# 74. Activities API

```text
GET    /api/activities
GET    /api/activities/{id}
POST   /api/activities
PUT    /api/activities/{id}
DELETE /api/activities/{id}
```

---

# 75. Calendar API

```text
GET    /api/calendar/events
GET    /api/calendar/events/{id}
POST   /api/calendar/events
PUT    /api/calendar/events/{id}
DELETE /api/calendar/events/{id}
```

---

# 76. Notifications API

```text
GET    /api/notifications
GET    /api/notifications/unread-count
PATCH  /api/notifications/{id}/read
PATCH  /api/notifications/read-all
```

---

# 77. Reports API

Examples:

```text
GET /api/reports/dashboard
GET /api/reports/sales
GET /api/reports/leads
GET /api/reports/activities
GET /api/reports/revenue
GET /api/reports/pipeline
GET /api/reports/sales-performance
```

Reports accept date/filter parameters.

---

# 78. Users API

```text
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
PATCH  /api/users/{id}/disable
DELETE /api/users/{id}
PATCH  /api/users/{id}/reset-password
```

Only authorized administrators should access administrative operations.

---

# 79. Settings API

```text
GET /api/settings
PUT /api/settings
GET /api/settings/profile
PUT /api/settings/profile
PUT /api/settings/security
PUT /api/settings/notifications
```

---

# 80. Database

MySQL is used as the primary database.

For local development:

```text
XAMPP
 └── MySQL
```

Recommended database:

```text
crm_portal
```

Create it through phpMyAdmin or MySQL CLI.

---

# 81. database/schema.sql

This file contains the database schema.

Main tables:

```text
users
roles
permissions
teams

leads
contacts
companies
deals
deal_stages

activities
tasks
calendar_events

notifications
notes
tags

lead_tags
contact_tags
company_tags
deal_tags

audit_logs
```

The schema should contain:

* Primary keys
* Foreign keys
* Indexes
* Unique constraints
* Created timestamps
* Updated timestamps
* Soft-delete fields where appropriate

---

# 82. database/seed.sql

Contains fictional development/demo data.

The seed should contain at minimum:

```text
20+ Leads
20+ Contacts
10+ Companies
15+ Deals
Activities
Tasks
Notifications
Users
Roles
Permissions
Teams
Tags
Deal stages
```

All demo information must be fictional.

---

# 83. Database Relationships

Main relationships:

```text
User
 ├── Leads
 ├── Contacts
 ├── Companies
 ├── Deals
 ├── Activities
 └── Tasks

Company
 ├── Contacts
 ├── Deals
 └── Activities

Contact
 └── Deals

Lead
 ├── Activities
 ├── Tasks
 └── Deals

Deal
 ├── Activities
 └── Tasks
```

---

# 84. Database Indexing

Indexes should be added to commonly queried fields.

Examples:

```text
users.email
users.status

leads.email
leads.status
leads.source
leads.owner_id
leads.created_at

contacts.email
contacts.company_id
contacts.owner_id

companies.company_name
companies.owner_id

deals.stage_id
deals.owner_id
deals.expected_close_date

tasks.due_date
tasks.status
tasks.priority
tasks.assigned_user_id

notifications.user_id
notifications.read
```

This improves search and filtering performance.

---

# 85. Soft Deletion

Records that should not immediately disappear from the database can use:

```text
deleted_at
```

For example:

```text
Lead
Contact
Company
Deal
User
```

Normal queries exclude deleted records.

This protects historical information and helps with recovery/auditing.

---

# 86. Authentication

Authentication flow:

```text
React Login
     |
     v
POST /api/auth/login
     |
     v
Spring Security
     |
     v
Verify user
     |
     v
Verify password hash
     |
     v
Create authenticated session/token
     |
     v
Return authenticated user
```

Passwords are hashed using a secure password hashing algorithm such as BCrypt.

Never store:

```text
password
plainPassword
```

in the database.

Only the password hash is stored.

---

# 87. RBAC

The CRM uses:

```text
Role
   ↓
Permissions
   ↓
API authorization
```

Roles:

```text
Super Admin
Admin
Manager
Sales Representative
Support Agent
Viewer
```

Permissions:

```text
VIEW
CREATE
EDIT
DELETE
EXPORT
MANAGE_USERS
MANAGE_SETTINGS
```

Example:

```text
GET /api/leads
```

requires appropriate lead view permission.

```text
POST /api/leads
```

requires create permission.

```text
DELETE /api/leads/{id}
```

requires delete permission.

The backend must reject unauthorized requests even if someone manually calls the API.

---

# 88. Frontend Authorization

The frontend may hide buttons such as:

```text
Delete
Create User
Edit Settings
Export
```

based on permissions.

However:

```text
Hidden UI ≠ Security
```

Spring Security remains the authoritative authorization layer.

---

# 89. Rate Limiting

Rate limiting should protect sensitive endpoints, especially:

```text
/api/auth/login
/api/auth/forgot-password
/api/auth/reset-password
```

This helps prevent brute-force attacks and password-reset abuse.

---

# 90. Environment Variables

Never commit secrets.

Frontend environment example:

```text
VITE_API_URL=http://localhost:8080/api
```

Backend configuration should use environment variables for sensitive configuration.

Example:

```text
DB_HOST
DB_PORT
DB_NAME
DB_USERNAME
DB_PASSWORD
JWT_SECRET
MAIL_HOST
MAIL_USERNAME
MAIL_PASSWORD
```

Do not put production passwords into Git.

---

# 91. Backend application.properties

The backend configuration controls:

```text
Server port
MySQL connection
JPA/Hibernate
Logging
CORS
Security
```

Local development typically runs:

```text
http://localhost:8080
```

---

# 92. MySQL/XAMPP Setup

Start XAMPP.

Enable:

```text
Apache
MySQL
```

Apache is not required for Spring Boot itself, but XAMPP can provide phpMyAdmin for database management.

Open phpMyAdmin and create:

```text
crm_portal
```

Then configure Spring Boot to connect to:

```text
localhost:3306
```

---

# 93. Backend Startup

From the backend directory:

```bash
mvn clean install
```

Then:

```bash
mvn spring-boot:run
```

Backend should become available at:

```text
http://localhost:8080
```

---

# 94. Frontend Startup

From the frontend directory:

```bash
npm install
```

Then:

```bash
npm run dev
```

Vite normally starts the frontend at a local development URL such as:

```text
http://localhost:5173
```

The exact port can be configured in Vite.

---

# 95. Full Local Startup Order

Recommended startup sequence:

```text
1. Start XAMPP
2. Start MySQL
3. Verify crm_portal database
4. Start Spring Boot
5. Start React
6. Open React application
7. Login
```

Architecture:

```text
Browser
   |
   | http://localhost:5173
   v
React
   |
   | http://localhost:8080/api
   v
Spring Boot
   |
   | JDBC
   v
MySQL
   |
   v
XAMPP
```

---

# 96. Global Search

Global search is available from the top navigation.

It searches:

```text
Leads
Contacts
Companies
Deals
Tasks
```

Results should identify their record type.

Example:

```text
John Smith
Lead

Acme Technologies
Company

Enterprise Renewal
Deal
```

Selecting a result navigates to its details page.

---

# 97. Quick Create

The top navigation contains a quick-create button.

Possible actions:

```text
New Lead
New Contact
New Company
New Deal
New Task
New Activity
New Calendar Event
```

Permission checks determine which actions are available.

---

# 98. Notifications

The top navigation displays an unread notification count.

Example:

```text
🔔 4
```

Opening notifications displays:

```text
New lead assigned
Deal moved to Proposal
Task due tomorrow
Meeting reminder
```

---

# 99. Loading States

Every API-driven screen must provide a loading state.

Examples:

```text
Skeleton loaders
Spinners
Loading buttons
Disabled form submission
```

Avoid blank screens while waiting for API responses.

---

# 100. Empty States

When no data exists, provide useful empty states.

Example:

```text
No leads found

Try changing your filters or create your first lead.

[Create Lead]
```

---

# 101. Error States

API failures should display useful messages.

Example:

```text
Unable to load leads.

Please try again.

[Retry]
```

Do not expose internal Java stack traces to users.

---

# 102. Toasts

Use consistent feedback.

Success:

```text
Lead created successfully.
```

Error:

```text
Unable to create lead.
```

Warning:

```text
This action cannot be undone.
```

---

# 103. Confirmation Dialogs

Destructive operations require confirmation.

Examples:

```text
Delete lead?
Delete company?
Remove user?
Disable user?
Delete deal?
```

The dialog should explain the action clearly.

---

# 104. Responsive Design

The application supports:

```text
Desktop
Tablet
Mobile
```

Desktop:

```text
Sidebar + Content
```

Tablet:

```text
Collapsible Sidebar + Content
```

Mobile:

```text
Top Bar
Drawer Navigation
Full-width Content
```

Tables should become horizontally scrollable or use responsive card layouts where appropriate.

---

# 105. Dark Mode

The application supports:

```text
Light
Dark
System preference
```

Dark mode should apply consistently to:

* Sidebar
* Cards
* Tables
* Forms
* Modals
* Charts
* Calendar
* Dropdowns
* Toasts

---

# 106. Accessibility

The UI should provide:

* Semantic HTML
* Keyboard navigation
* Visible focus states
* Accessible labels
* Proper button labels
* ARIA attributes where necessary
* Sufficient color contrast
* Keyboard-accessible modals
* Keyboard-accessible dropdowns
* Screen-reader-friendly form errors

Do not use color as the only way to communicate status.

---

# 107. API Response Format

A consistent API response structure is recommended.

Success:

```json
{
  "success": true,
  "message": "Lead created successfully",
  "data": {}
}
```

Paginated response:

```json
{
  "success": true,
  "data": [],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 100,
    "totalPages": 5
  }
}
```

Error:

```json
{
  "success": false,
  "message": "Validation failed",
  "code": "VALIDATION_ERROR",
  "errors": {}
}
```

---

# 108. Pagination

API pagination uses parameters such as:

```text
?page=0&size=20
```

Sorting:

```text
?sort=createdAt&direction=desc
```

Searching:

```text
?search=john
```

Filtering:

```text
?status=QUALIFIED
```

Multiple filters can be combined.

---

# 109. Export

Major tables should support export.

Supported formats can include:

```text
CSV
Excel-compatible CSV
```

Exports should respect the user's permissions.

Users without export permission must receive:

```text
403 Forbidden
```

---

# 110. Import

Lead and contact import should support CSV.

Import flow:

```text
Select CSV
    ↓
Upload
    ↓
Validate headers
    ↓
Validate rows
    ↓
Show errors
    ↓
Import valid records
    ↓
Show import summary
```

Example:

```text
Total rows: 100
Imported: 94
Failed: 6
```

---

# 111. Audit Logging

Security-sensitive operations should create audit records.

Examples:

```text
User login
Failed login
Logout
Password change
Password reset
User creation
Role change
User disable
Settings change
Lead deletion
Deal stage change
```

Audit logs should not expose passwords or sensitive credentials.

---

# 112. Communication Service

Email is abstracted behind a service interface.

Architecture:

```text
CommunicationController
        ↓
EmailService
        ↓
EmailProvider
        ↓
SMTP / External Provider
```

For development:

```text
MockEmailProvider
```

can store messages locally without requiring external credentials.

Production can later use:

```text
SMTP
SendGrid
Amazon SES
Mailgun
```

without changing the React application.

---

# 113. Reports

Reports should be calculated by the backend rather than calculating business-critical totals only in React.

Examples:

```text
Pipeline Value
Weighted Pipeline
Revenue
Won Deals
Lost Deals
Conversion Rate
Average Deal Size
```

Formula examples:

```text
Pipeline Value
= Sum of active deal values

Weighted Pipeline
= Sum(deal value × probability)

Conversion Rate
= Converted Leads / Total Leads × 100
```

---

# 114. Security Rules

The application must follow these rules:

```text
Never store plaintext passwords.
Never expose database credentials to React.
Never expose JWT secrets to React.
Never trust frontend permissions.
Validate all API input.
Authorize all protected APIs.
Use parameterized database queries through JPA.
Do not expose stack traces.
Log security events.
Rate-limit authentication endpoints.
Use HTTPS in production.
```

---

# 115. Development Database

Development database:

```text
Database: crm_portal
Host: localhost
Port: 3306
```

The exact username/password should be stored in local environment configuration rather than committed into Git.

---

# 116. Production Architecture

A production deployment can use:

```text
                    Internet
                       |
                       v
                Reverse Proxy
                 HTTPS / SSL
                       |
          -------------------------
          |                       |
          v                       v
      React App             Spring Boot API
                                  |
                                  v
                              MySQL
```

Secrets should be provided through:

```text
Environment variables
Secret manager
Deployment platform secrets
```

---

# 117. Testing

Backend tests should cover:

```text
Authentication
Authorization
Lead CRUD
Contact CRUD
Company CRUD
Deal CRUD
Task CRUD
Activity CRUD
Validation
Permissions
Reports
```

Frontend tests should cover important:

```text
Forms
Authentication flows
Tables
Filters
Navigation
Permission-based UI
Critical CRUD operations
```

---

# 118. Recommended Backend Test Structure

```text
backend/src/test/java/
└── com/crm/portal/
    ├── controller/
    ├── service/
    ├── repository/
    └── security/
```

---

# 119. Recommended Frontend Test Structure

```text
frontend/src/
└── __tests__/
    ├── auth/
    ├── leads/
    ├── contacts/
    ├── deals/
    └── components/
```

---

# 120. Documentation

The `docs/` directory contains detailed technical documentation.

```text
API.md
DATABASE.md
AUTHENTICATION.md
RBAC.md
DEPLOYMENT.md
```

## API.md

Documents:

* Endpoints
* Request bodies
* Responses
* Authentication
* Error codes
* Pagination
* Filtering

## DATABASE.md

Documents:

* Tables
* Relationships
* Indexes
* Foreign keys
* Seed data

## AUTHENTICATION.md

Documents:

* Login
* Logout
* Password reset
* Sessions
* Security

## RBAC.md

Documents:

* Roles
* Permissions
* Authorization rules

## DEPLOYMENT.md

Documents:

* Production build
* Environment variables
* Database setup
* Backend deployment
* Frontend deployment

---

# 121. Complete Feature Flow

Example: Creating a Lead.

```text
User opens Leads
       ↓
Clicks "Create Lead"
       ↓
React LeadForm
       ↓
Client validation
       ↓
leadService.createLead()
       ↓
POST /api/leads
       ↓
Spring Security authentication
       ↓
Permission check
       ↓
Request DTO validation
       ↓
LeadService
       ↓
LeadRepository
       ↓
MySQL
       ↓
Lead created
       ↓
Audit log
       ↓
API response
       ↓
React updates UI
       ↓
Success toast
```

This is the required end-to-end pattern for the CRM.

---

# 122. Complete Deal Stage Flow

```text
User drags Deal
       ↓
Kanban updates UI
       ↓
PUT /api/deals/{id}/stage
       ↓
Authentication
       ↓
Authorization
       ↓
Validate stage
       ↓
DealService
       ↓
Update database
       ↓
Create audit log
       ↓
Create notification if necessary
       ↓
Return updated deal
       ↓
React refreshes pipeline
```

---

# 123. Folder Responsibility Summary

```text
frontend/
    React application

backend/
    Spring Boot application

database/
    SQL schema and seed scripts

docs/
    Technical documentation
```

Inside React:

```text
components/
    Reusable UI

pages/
    Application screens

services/
    API communication

context/
    Global application state

hooks/
    Reusable React logic

routes/
    Application routing/security

utils/
    Utility functions

styles/
    Global styling
```

Inside Spring Boot:

```text
controller/
    REST endpoints

service/
    Business logic

repository/
    Database access

entity/
    Database models

dto/
    API request/response objects

mapper/
    Entity ↔ DTO conversion

security/
    Authentication/authorization

validation/
    Validation

exception/
    Centralized error handling

config/
    Application configuration

enums/
    Application constants

specification/
    Dynamic database filtering
```

---

# 124. Important Development Rule

Do not put the following into React:

```text
MySQL credentials
Database queries
Password hashing
Authorization logic as the only security mechanism
JWT signing secrets
SMTP passwords
Third-party API secrets
```

React communicates with Spring Boot.

Spring Boot communicates with MySQL.

---

# 125. Final Architecture

```text
┌───────────────────────────────────────────────┐
│                  React.js                     │
│                                               │
│ Dashboard | Leads | Contacts | Companies     │
│ Deals | Tasks | Calendar | Reports           │
│ Users | Settings | Notifications              │
│                                               │
│ Components / Pages / Services / Hooks         │
└──────────────────────┬────────────────────────┘
                       │
                       │ REST / JSON
                       ▼
┌───────────────────────────────────────────────┐
│                 Spring Boot                   │
│                                               │
│ Controllers                                   │
│       ↓                                       │
│ Services                                      │
│       ↓                                       │
│ Repositories                                  │
│       ↓                                       │
│ JPA / Hibernate                               │
│                                               │
│ Spring Security                               │
│ RBAC                                          │
│ Validation                                    │
│ Audit Logging                                 │
│ Exception Handling                            │
└──────────────────────┬────────────────────────┘
                       │
                       │ JDBC
                       ▼
┌───────────────────────────────────────────────┐
│                    MySQL                      │
│                                               │
│ Users / Roles / Permissions / Teams           │
│ Leads / Contacts / Companies / Deals          │
│ Activities / Tasks / Calendar                 │
│ Notifications / Notes / Tags                 │
│ Audit Logs                                    │
└───────────────────────────────────────────────┘
                       │
                       ▼
                    XAMPP
                 MySQL Server
```

---

# 126. Definition of Done

The CRM should be considered complete only when:

* React application runs successfully.
* Spring Boot backend runs successfully.
* MySQL database connects successfully.
* Login works.
* Logout works.
* Password reset works.
* Protected routes work.
* RBAC works.
* Backend permissions are enforced.
* Database schema is created.
* Seed data is available.
* Leads CRUD works.
* Contacts CRUD works.
* Companies CRUD works.
* Deals CRUD works.
* Deal Kanban works.
* Activities work.
* Tasks work.
* Calendar works.
* Notifications work.
* Communications history works.
* Reports calculate real database data.
* Users and teams can be managed.
* Settings work.
* Global search works.
* Tables support pagination/filtering/sorting.
* Export works where permitted.
* Validation works on frontend and backend.
* Errors are handled consistently.
* Audit logging works.
* Responsive design works.
* Dark/light themes work.
* Accessibility basics are implemented.
* No production secrets are committed.
* No major feature depends on hard-coded mock data.

---

# 127. Local Development Checklist

* [ ] Install Node.js
* [ ] Install Java JDK
* [ ] Install Maven
* [ ] Install XAMPP
* [ ] Start MySQL from XAMPP
* [ ] Create `crm_portal` database
* [ ] Configure backend environment variables
* [ ] Run database schema/migrations
* [ ] Run seed data
* [ ] Run Spring Boot
* [ ] Install frontend dependencies
* [ ] Configure frontend API URL
* [ ] Run React
* [ ] Open the application
* [ ] Login with seeded development account
* [ ] Verify dashboard
* [ ] Verify CRUD operations
* [ ] Verify permissions
* [ ] Verify reports
* [ ] Verify notifications
* [ ] Verify search
* [ ] Verify exports

---

# 128. Important Note About the Technology Stack

The requested stack is:

```text
Frontend
React.js + JavaScript

Backend
Java + Spring Boot

Database
MySQL

Local Database Environment
XAMPP

API
REST

Database Access
Spring Data JPA / Hibernate

Build
npm + Maven
```

This stack should be kept consistent rather than introducing another backend framework or replacing MySQL.

The React frontend must never connect directly to MySQL. All database operations should pass through the Spring Boot API.

---
