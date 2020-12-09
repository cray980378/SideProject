# SimpleBlog
這是一個簡單的個人部落格。

---

如何執行？

請先安裝 Java 執行環境，以及 Docker Compose，並執行以下指令開啟 MySQL

```bash
cd docker
docker-compose up -d
```

接著再執行以下指令執行此專案

```bash
cd ..
./gradlew bootrun
```

首頁預設 URL : http://127.0.0.1:8080/

後台預設 URL : http://127.0.0.1:8080/admin

此專案再第一次開啟時，會自動建立一個預設的後台管理者帳號，其帳號及密碼皆為 admin
