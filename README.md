# DA_ex1

Currently, the Main contains hardcoded simulation of the [Example 1][1] :  
Compile and run with: `./buildAndRun.py`  
(make sure you have execute permission w/ `chmod +x buildAndRun.py`)  

TODO:
- processList, messageList and eventList could be converted to List if needed
- Event interface pretty ugly atm `new Event(processList[2], Event.Type.SEND, messageList[0]),`  
  better would be sth like `new Event(1, Type.SEND, 1),  // new Event(int processId, Type t, int messageId),` or sth

- implement classes properly (vector clock, msg buffer, history...)
- implement algorithm logic (splitted between onReceiveEvent and onSendEvent -> look at flowchart)
- test if it works correctly (try multiple scenarios)
- convert it to distributed system, using RMI
- test again

*Problems*  11/26/2019
- Message should be Message(src, dst, VectorClk) so that we can compare the vectorclk upon delivery.
So how shall we change Messages in messageList.Main?
example:    new Message(3, 1) should be: new Message(3, 1, src.vectorclk)

JUR comments 11/26/2019:
- Process (and all classes) have mostly public fields, bcs it simpler to handle 
  (otherwise you'd need getters and setters) which in our case is overkill/not needed imo.
- I removed numProcesses from Process constructor, bcs I hoped that the new class VectorCLock will handle that
  (but then couldn't think of how, so maybe we still need it)
- I'm not sure if `onSendEvent(Message m)` will work with RMI. If the Process is on another machine and we pass 
  a reference to an object, will it modify the object on the host machine? We'll see when we play with RMI


JUR comments 12/26:
- Process class now seems more or less finished (structure-wise, SES logic needs to be implemented)
TODO:
- delivery test, onDeliver event, check onSend and onReceive (was tired so might contain algorithmic mistakes)
- don't be afraid to mess around, delete, change or do anything with the code -> its yours now :)
- when the algorithm works -> make it RMI=ish, assemble the processList with IDs from RMI, pray.

[1]: https://youtu.be/y5HvzJjYhv8?t=176  