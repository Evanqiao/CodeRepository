#include <MultiHopLqi.h>
 generic configuration CollectionReceiveC(collection_id_t id) {
 	provides {
 		interface Receive;
 		interface Packet;
 	}
 }
 implementation {
 	components CollectionC;

 	Receive = CollectionC.Receive[id];
 	Packet = CollectionC;
 }