YARN - Yet Another Resource Negotiator, służy do zarządzania zasobami 

yarn.resourcemanager.scheduler.class=org.apache.hadoop.yarn.server.resourcemanager.scheduler.capacity.CapacityScheduler  - ustawienie programu szeregującego (domyślnie 
mapreduce.job.queuename - nazwa kolejki wykorzystywana przez mapreduce. domyślnie default. Aby zmienić wystarczy wpisać dev zamiast root.dev (zostanie wyrzucony wyjątek). 
Kolejka Capcity

yarn.scheduler.capacity. <sciezka_do_kolejki>.wlasciwosc - gdzie sciezka_do_kolejki jest np. root.dev
kolejki ustawia się w: capacity-scheduler.xml

Fair 
Domyślne dla dystrybucji CDH. 

org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FairScheduler 
yarn.scheduler.fair.allocation.file - wskazuje na sciezke dla pliku konfiguracyjnego. fair-scheduler.xml
defaultQueueSchedulingPolicy=fair - domyślny sposób szeregowania dla wszystkich kolejek
W xmlu schedulingPolicy można zmienić sposób szeregowania dla konkretnej kolejki. Oprócz fair jest FIFO i drf (Dominant Resource Fairness) 


queuePlacementPolicy - reguły rozmieszczania zadań w kolejkach
specified - aplikacja umieszczana jest w podanej kolejce
primaryGroup - kolejka pasująca do nazwy grupy użytkownika 
user - kolejka nazwy użytkownika
rule name="default" można osiągnąć również: 
yarn.scheduler.fair.user-as-default-queue=false - aplikacja jest umieszczania w domyślnej kolejce
yarn.scheduler.fair.allow-undeclared-pools=false - użytkownik nie może tworzyć własnych kolejek
W ten sposob nie trzeba tworzyć plików alokacji tj.fair-scheduler.xml
Jeśli brakuje zasobów na wykonanie zadania Fair stosuje wywłaszczanie. 
yarn.scheduler.fair.preemption=true - wywlaszczanie ustawione globalnie 
defaultMinSharePreemptionTimeout - wywlaczanie poprzez czas w sekundach, ustawienie globalne w pliku alokacji (xmlu)
minSharePreemptionTimeout - wywlaszczanie na poziomie kolejki
defaultFairSharePreemptionTimeout - stosowane keidy kontener ma za mało zasobów i wywłaszcza inne zadania

Szeregowanie z opóźnieniem
Stosowane zarówno przez capacity jak fair. 
yarn.scheduler.capacity.node-locality-delay - szeregowanie z opóźnieniem, liczba możliwości zaszeregowania 
yarn.scheduler.fair.locality.threshold.node=0.5 - program szergujący odczeka aż połowa węzłów przedstawi swoje możliwości zaszerowania a następnie wybiera węzeł z tej samej szafki 
yarn.scheduler.fair.locality.threshold.rack - próg po którym wybierana jest inna szafka 

 Dominant Resource Fairness (drf)
 Rozdziela zasoby z uwzględniem używanych zasobów np. pamięci, czasu procesora. Domyślnie jes wg pamięci. 
 W Capacity włącza się: 
 yarn.scheduler.capacity.resource-calculator=org.apache.hadoop.yarn.util.resource.DominantResourceCalculator
 W Fair wlacza sie: 
 defaultQueueSchedulingPolicy=drf
 





