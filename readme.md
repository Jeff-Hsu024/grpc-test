# gRPC 測試專案

這是一個使用 Java、Spring Boot 和 gRPC 的簡單問候服務範例。

## 專案結構

此專案包含兩個 Maven 模組：

*   `grpc-server`：一個 Spring Boot 應用程式，它會啟動一個 gRPC 伺服器，監聽 9090 連接埠。
*   `grpc-client`：一個 Spring Boot 應用程式，它會連線到 gRPC 伺服器並呼叫服務。

## gRPC 服務定義

服務是在 `src/main/proto/test.proto` 中定義的。

```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "custom.tibame201020.grpc_client.proto";
option java_outer_classname = "GreeterProto";

service Greeter {
  rpc SayHello (HelloRequest) returns (HelloResponse);
}
message HelloRequest {
  string name = 1;
}
message HelloResponse {
  string message = 1;
}
```

這定義了一個 `Greeter` 服務，其中包含一個 `SayHello` 方法，它會接收一個包含使用者名稱的請求，並回傳一個問候訊息。

## 如何執行

您需要分別在不同的終端機中啟動伺服器和客戶端。

### 1. 執行伺服器

```bash
cd grpc-server
mvn spring-boot:run
```

伺服器將會啟動並在 9090 連接埠上監聽 gRPC 請求。

### 2. 執行客戶端

```bash
cd grpc-client
mvn spring-boot:run
```

客戶端將會啟動，連線到伺服器，發送一個 `SayHello` 請求，並在主控台印出回應。
