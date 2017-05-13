I've chosen to achieve concurrency through immutability, stateless, and message-driven approach.
I've kept the synchronizing small, and not in my code.

Synchronization between the thread(s) that places bets and the thread that consumes them is
 done inside a LinkedTransferQueue.

 The same king of synchronization could be achieve using the synchronized keyword (bad practise),
 a volative keyword (bad practise), an AtomicReference to a collection of bets.

 I've chosen the TransferQueue solution because it's explicitly documented in the Java API and
 a junior developer could easily read and understand the code.



I assume the Money class has been fully tested and is part of a common library.

Things to improve:
- provide an external messages.properties file in replacement of the hard-coded messages
- with more time, I would use BDD (Cucumber) for integration tests
    as it's easy to extract business rules here

 Extra: I've added a REST controller that allows placing bets using a JSON request.
 It's only a few lines of extra code and proves the good modularity of the solution.


ERROR HANDLING with internationalized messages
ERROR HANDLING is still perfectible (would create proper exception)
handling multi-currency
money storage can be improved to handle longer values