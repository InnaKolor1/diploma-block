# Платформа по перепродаже вещей- Бэкенд

## Описание проекта
 Rest API сервис для платформы объявлений о продаже вещей.
 
# Текущий статус 
Бэкенд реализован

## Уже реализовано
✅Этап 1: Написание DTO и контроллеров

####
✅Этап 2:Сущности, репозитории, маперы

####
✅Этап 3:Авторизация, аутентификация, сервисы

####
✅Этап 4: Работа с изображениями, тестирование

## 📁 Структура проекта
```
src/main/java/ru/skypro/homework/
├── config/          # Конфигурационные классы
├── controller/      # REST контроллеры
├── dto/            # Data Transfer Objects
├── entity/         # JPA сущности
├── handler/        # Обработчики исключений
├── mapper/         # MapStruct мапперы
├── repository/     # Spring Data репозитории
└── service/        # Бизнес-логика
```

### DTO (Data Transfer Object)
- [x] `Login.java` - аутентификация
- [x] `Register.java` - регистрация
- [x] `NewPassword.java` - смена пороля
- [x] `User.java` - данные пользователя
- [x] `UpdateUser.java` - обновления профиля
- [x] `Ad.java` - объявления
- [x] `ExtendedAd.java` - детали объявления
- [x] `Ads.java` - список объявлений
- [x] `CreateOrUpdateAd.java` - создание/обновление
- [x] `Comment.java` - комментарий
- [x] `Comments.java` - список комментариев
- [x] `CreateOrUpdateComment.java` - создание/оюновление
- [x] `Role.java` - роли пользователей

### Контролерры
- [x] `ImageController.java` - /images
- [x] `AuthController.java` - /login
- [x] `UserController.java` - /users/**
- [x] `AdsController.java` - /ads/**
- [x] `CommentsController.java` - /ads/*/coments/**

### Сущности (Entity)
- [x] `AdEntity.java` - объявление
- [x] `CommentEntity.java` - комментарий
- [x] `UserEntity.java` - пользователь


## 👥 Команда разработчиков
- Куцак Инеза Демуровна
- Морозова Мария Николаевна

## 🛠 Технологии и библиотеки
- **Java 17**
- **Spring Boot** (Web, Security, Data JPA)
- **PostgreSQL** - реляционная база данных
- **Liquibase** - управление миграциями БД
- **MapStruct** - маппинг объектов
- **OpenAPI** - документация API
- **Lombok** - сокращение кода
- **Maven** - сборка проекта

## 🚀 Запуск проекта

### Предварительные требования:
1. Установить Java 17
2. Установить PostgreSQL
3. Создать базу данных `diplom_bd`

### Настройка базы данных:
```sql
CREATE DATABASE diplom_bd;
CREATE USER postgres WITH PASSWORD '1234';
GRANT ALL PRIVILEGES ON DATABASE diplom_bd TO postgres;
```

### Запуск:
1. Клонировать репозиторий
2. Настроить `application.properties` (указать данные БД)
3. Запустить приложение:
```bash
mvn spring-boot:run
```

### Тестовые пользователи:
- **Админ:** admin@gmail.com / пароль: 1234
- **Пользователь:** user@gmail.com / пароль: 1234

## 📚 API Документация
После запуска приложения документация доступна по адресу:

- OpenAPI спецификация: http://localhost:8081/v3/api-docs

## 🔐 Роли и доступы
- **USER**: Может создавать/редактировать/удалять свои объявления и комментарии
- **ADMIN**: Полный доступ ко всем функциям системы