

## JAVA版本：JAVA11

## 后端配置方法

### 1. Idea Intellij 配置启动

### 2. 命令行启动

进入repairJavaBack目录下

使用maven进行项目管理

运行：

```
mvn spring-boot:run
```

## 前端配置

进入repairJavaFront目录下

使用npm进行项目管理

运行：

```
npm install #可能由于网络原因下载失败
npm run serve
```

## 数据库配置

运行initDataset.sql(注意文件中的路径需要进行相应修改)

修改repairJavaBack/src/main/resources/application.yml

## 数据文件路径配置

修改repairJavaBack/src/main/resources/application.yml中的data-path

## 编译命令行

根据操作系统修改repairJavaBack/src/main/java/repairjava/entity/program目录下

EnvironmentConfig.java中的配置变量

```
public static final String SHELL = "/bin/sh";
public static final String OPTION = "-c";
public static final String SLASH = "/"
```

根据操作系统修改repairJavaBack/src/main/java/repairjava/entity/monitor目录下

JDIDebugger.java和JDIDebuggerExecutor.java命令行中的";" 和 ":"

如

```
//windows
String compileSingleJunitCMD = String.format("javac -g -cp %s;%s;. %s", TOOL_PATH, JUNIT_PATH, SINGLE_JUNIT_PATH);
//linux
String compileSingleJunitCMD = String.format("javac -g -cp %s:%s:. %s", TOOL_PATH, JUNIT_PATH, SINGLE_JUNIT_PATH);
```

## 换行符

根据操作系统修改repairJavaFront/src/components目录下

RepairVariableMatchTable.vue和RepairStatementMatchTable.vue中的字符串解析换行符

如RepairVariableMatchTable.vue中104行

```
//windows
let correctVarCodeLines = this.correctCode.trim().split('\r\n');
//linux
let correctVarCodeLines = this.correctCode.trim().split('\n');
```

## 待修复java文件配置

删去java文件包名



