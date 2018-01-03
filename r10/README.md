# hadoop

net.topology.node.switch.mapping.impl = definiuje implementacje DNSToSwitchMapping i służy do lokalizacji sieciowych węzłów roboczych. Domyślnie używana jest implementacja ScriptBasedMapping
net.topology.script.file.name=  można użyć skryptu od odnalezienia/odwzorowania węzłów/szafek https://wiki.apache.org/hadoop/topology_rack_awareness_scripts domyślnie jest /default-rack czyli jeden węzeł

hdfs namenode -format - formatuje, kolejne węzły danych można dodawać bez formatowania i nie trzeba określać rozmiaru systemu plików 

/etc/hadoop/conf/slaves - plik slaves, znajduje się lista węzłów, można podać adres IP jak też nazwę. jest to lista węzłów danych, menedżerów węzłów.
hdfs getconf -namenodes - określa nazwę hosta węzła nazw, szuka właściwość zapisaną w fs.defaulFS
hdfs getconf -secondarynamenodes - nazwa pomocniczego węzła nazw


start-dfs.sh - uruchamia domyślne demony HDFS
start-yarn.sh - skrypt uruchamia  menadżera zasobów lokalnie, uruchamia menadżera węzła w każdej maszynie z pliku slaves
mr-jobhistory-deamon.sh start historyserver - uruchamia serwer historii zadań

Tworzenie katalogow dla userow wraz z przydzialem  miejsca 
hadoop fs -mkdir /user/username
hadoop fs -chown username:username /user/username
hdfs dfsadmin -setSpaceQuota 1t /user/username

w pliku hadoop-env.sh można zmienić pamięć dla węzła nazw HADOOP_NAMENODE_OPTS opacja -Xmx2000m wzór 200 (węzłów) x 24 TB (na węzeł) / 128 MB (wielkość bloku) x 3 (repliki) wynikiem jest ile ustawić pamięci dla węzła nazw

HADOOP_IDEN_STRING - identyfikator hadoopa w logach, ustawia się hadoop-env.sh

HADOOP_SSH_OPTS = przekazuje ustawienia ssh, niezbędne do komunikacji między węzłami. ConnectionTimeout - jeśli jest zbyt nieski można pominąć obciążone serwery. 
StrictHostKeyChecking=no - pomija odpytywanie o klucz, domyślnie ask, 

http://localhost:8088/conf - lista konfiguracji z listą jaki plik zawiera daną konfigurację 

dfs.namenode.name.dir=hadoop/hdfs/namenode - określa gdzie znajdują metadane węzła nazwa. Konfiguruje się tak aby był przechowywany na osobnym NFSie. 
dfs.datanode.data.dir=/hadoop/hdfs/data w pliku hdfs-site.xml - określa listę katalogów węzła danych gdzie przechowywane są bloki. Katalog powinien być na każdy dysk. Aby zwiększyć odczyt dysk powinien być zamontowany z opcją noatime

dfs.namenode.checkpoint.dir=hadoop/hdfs/namesecondary - przechowywanie punktów kontrolnych, 

System YARN (yarn-site.xml)

yarn.resourcemanager.hostname=sandbox-hdp.hortonworks.com  = określa nazwę lub IP hosta menadżera zasobów
yarn.resourcemanager.address=sandbox-hdp.hortonworks.com:8032 (IP:port)
yarn.nodemanager.local-dirs=/hadoop/yarn/local - miejsce przechowywania danych wyjściowych z operacji mapowania, katlogi mogą być odzielone przecinkami 
yarn.nodemanager.aux-services - usługi wspomogające podczas przestawiania danych (shuffle), domyślnie brak wartości, w hortonworks ustawione jest: mapreduce_shuffle,spark_shuffle,spark2_shuffle
yarn.nodemanager.resource.memory-mb=3000 - ilość pamięci przydzilonej kontenerowi, domyślnie 8192 MB
yarn.nodemanager.resource.cpu-vcores=8 - ilość rdzeni przydzielonych kontenerowi, powinien być ilość rdzeni minus proces demona
yarn.nodemanager.vmem-pmem-ratio=5 - ilość użycia wirtualnej pamięci, ile razy pamięć wirtualna może przekraczać pamięć fizyczną
mapreduce.map.memory.mb - ilość pamięci dla kontenera dla procesu mapowania 
mapreduce.reduce.memory.mb - ilość pamięci dla kontenera dla procesu redukowania 
yarn.scheduler.minimum-allocation-mb - minimalna pamięć dla programów szeregujących w YARN
yarn.scheduler.maksymalna-allocation-mb - maksymalna pamięć dla programów
yarn.nodemanager.container-executor.class - określa wykonawcę kontenera dla menadżera węzła. W hortonworks: org.apache.hadoop.yarn.server.nodemanager.DefaultContainerExecutor. Jeśli chcemy ograniczyć powinniśmy użyć klasy LinuxContainerExecutor. I trzeba odpowiednio ustawić rodzinę konfiguracji: yarn.nodemanager.linux-container-executor

http://localhost:50070 - węzeł nazw
http://localhost:50090/ - pomocniczy węzeł nazw
http://localhost:50075 - węzeł danych
http://localhost:8088 - menedżer zasobów
http://localhost:8042 - menedżer węzła. Ciakawa jest zakładka Tools. Zawiera: konfigurację w xmlu, logi, server stacks, metryki, errors
dfs.hosts.exclude=/etc/hadoop/conf/dfs.exclude - wskazuje na plik który zawiera listę serwerów wykluczonych
yarn.resourcemanager.nodes.exclude-path=/etc/hadoop/conf/yarn.exclude - 
io.file.buffer.size=131072 (core-site.xml) - bufor w operacjach wyjścia-wejścia. 
dfs.blocksize=134217728 - wielkość bloku (hdfs-site.xml)
fs.trash.interval=300 - ustawia po jakim czasie mają zostać usunięte pliki z kosza (Trasha). HDFS automatycznie usuwa pliki z kosza. 
hadoop fs -expunge - wymuszenie wyczyszczenia kosza tylko dla plików o życiu dłuższym niż ustawionym w parametrze powyżej
mapreduce.job.reduce.slowstart.completedmaps=0.05 - domyślnie 5 procent, proponowane 80%. Określa ile procent mapowania musi być zakończonych
dfs.client.read.shortcircuit=true - włącza skrócony odczyt danych lokalnych tj. pomija komunikację TCP/IP. 

hadoop fs -put text.txt . - kopiowanie lokalnego pliku 


