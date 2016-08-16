# wildfly-swarm-session-replication-demo

## Usage

### Run

``` sh
$ mvn wildfly-swarm:run
```

### Access to API

1st

``` sh
$ curl --dump-header /tmp/header localhost:8080 
Session ID:YD0KrWoflDjMS_jqcn9gs0angZM-YAdElLyUfin2, 1 Times Access.
```

2nd

```
$ curl -b /tmp/header localhost:8080 
Session ID:YD0KrWoflDjMS_jqcn9gs0angZM-YAdElLyUfin2, 2 Times Access.
```

### Session Replication

Run another node.

```
$ mvn wildfly-swarm:run -Dswarm.port.offset=100
```