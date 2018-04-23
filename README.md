OpuS atop Alluxio.


We provide the locations of the key implementation of OpuS atop Alluxio.

core/server/master/src/main/java/alluxio/master/OpuS/OpuSMaster.java 
Implements the key logic of cache allocation of OpuS; Records the historical access
frequencies of each client.


core/server/master/src/main/java/alluxio/master/OpuS/User.java
Provides the API for client to access the files cached by OpuS. 

python/OpuS.py
Called by the OpuSMaster to calculate the PF allocation.

python/FairRide_allocator.py
Called by the OpuSMaster to calculate the cache allocation under FairRide.

python/Isolated_Allocator.py
Called by the OpuSMaster to calculate the cache allocation in isolated caches.
