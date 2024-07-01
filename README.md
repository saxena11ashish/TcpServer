# TCP Server from Scratch in Java

### Socket Programming - a way to connect nodes over the network
### Server Implementation
1. We need to expose a port, over which the client will connect 
2. The server needs to actively listen on this port and any request on this port should be processed
3. Read and write are blocking operations
4. So care should be taken so that server can handle multiple requests at any point in time
5. To make the concurrent requests possible, we keep the main thread free to accept new connections
6. The responsibilty to handle each connection is given to a separate thread, which keeps the main thread free.
