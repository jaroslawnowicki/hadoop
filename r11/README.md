Skaner bloków węzła danych:
Raport: http://localhost:50075/blockScannerReport
Spawdane są sumy kontrolne. 
dfs.datanode.scan.period.hours=504 . Co ile ma skanowac bloki w wcelu wykrycia nieprawidłowych sum kontrolnych.

http://localhost:50075/blockScannerReport?listblocks
LIsta bloków z ostatnim znanym stanem.

Balacncer
start-balancer.sh - stosować w celu zrównoważenia bloków w węzłach danych zgodnie z konfiguracją rozmieszczenia bloków. W klastrze może działać tylko jeden balancer i teorytcznie działa cały czas w tle.
dfs.datanode.balance.bandwidthPerSec=1MB/s - ogranicza szybkość kopiowania bloków aby nie obciążać systemuhttp://localhost:8088/logLevel

Ustawianie poziomu logowania
http://localhost:8088/logLevel tutaj można ustawić poziom logowania dla pakietów.

http://localhost:8088/stacks - pobieranie ślady stosu np. menadżera zasobów

JMX 
HADOOP_NAMENODE_OPTS="-Dcom.sun.management.jmxremote.port=8004" i zapomocą JConsole
http://localhost:8088/jmx w formacie JSON. Wskaźniki można również monitorować w aplikacji Ganglia

Kopie zapasowe metadanych 
Tworzeniekopii metadanych węzła nazw:
hdfs dfsadmin -fetchImage fsimage.backup. Plik utworzony jest w kataogu /root

Dodawanie węzła
1. dodaj host do pliku (dfs.hosts, yarn.resourcemanager.nodes.include-path) 
2. sudo hdfs dfsadmin -refreshNodes
3. yarn rmadmin -refreshNodes
4. zaktualizować plik slaves
5. uruchomienie nowych węzłów



