# Kotlin-Executor
Sample Demo of Executor in Android - Language Kotlin
# Source - 

https://www.youtube.com/watch?v=KapFKr1K8-U 


# Welcome to the Kotlin-Executor!

* The Java ExecutorService is a construct that allows you to pass a task to be executed by a thread asynchronously. The executor service creates and maintains a reusable pool of threads for executing submitted tasks. The service also manages a queue, which is used when there are more tasks than the number of threads in the pool and there is a need to queue up tasks until there is a free thread available to execute the task.

* In this article, we'll focus on the ThreadPoolExecutor implementation of the ExecutorService interface. There are two ways to instantiate a Thread Pool Executor. You can either directly instantiate it using one of its constructor overloads or you can use one of the factory methods in the Executors class.

Let's look at a few examples.

Directly instantiating a ThreadPoolExecutor with 10 threads, a keepAliveTime of 0 milliseconds, and a LinkedBlockingQueue:

`ExecutorService executorService = `
          `new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,`
          `new LinkedBlockingQueue<Runnable>());`


* Instantiating a ThreadPoolExecutor with 10 threads using an Executors factory method:

`ExecutorService executor = Executors.newFixedThreadPool(10);`


* Instantiating a ThreadPoolExecutor with a single thread using an Executors factory method:

`ExecutorService executor = Executors.newSingleThreadExecutor();`


* Instantiating a ThreadPoolExecutor that adds threads to the pool as needed using an Executors factory method:

`ExecutorService executor = Executors.newCachedThreadPool();`


* When you instantiate your Executor Service, a few parameters are initialized. Depending on how you instantiated your Executor Service, you may manually specify these parameters or they may be provided for you by default. These parameters are: 

* corePool size
* maxPool size
* workQueue
* keepAliveTime
* threadFactory
 * rejectedExecutionHandler


Using one of the factory methods available in the Executors class simply selects some default values for the above for you based on your inputs. Executors.newSingleThreadExecutor() creates a pool with a core size of 1, max size of 1, a keepAliveTime of 0ms (which means that the thread in the pool would stay alive unless explicitly closed), an unbounded LinkedBlockingQueue, the default threadFactory, and the default rejectedExecutionHandler.

* Meanwhile, Executors.newFixedThreadPool(10) creates a pool with a core size of 10, max size of 10, a keepAliveTime of 0ms, an unbounded LinkedBlockingQueue, the default threadFactory, and the default rejectedExecutionHandler. 

# So what do all these parameters mean?

* The core pool size is the minimum number of threads that should be kept in the pool. The number of threads may grow to reach the max pool size (if it is higher than the core pool size), but in general, it represents the number of threads you expect to have alive in the pool. When a task is submitted to the executor, it checks if the actual running number of threads is less than the core pool size. If it is, then it creates a new worker using the specified threadFactory.

* The max pool size is the maximum number of workers that can be in the pool. If the max pool size is greater than the core pool size, it means that the pool can grow in size, i.e. more workers can be added to the pool. Workers are added to the pool when a task is submitted but the work queue is full. Every time this happens, a new worker is added until the max pool size is reached. If the max pool size has already been reached and the work queue is full, then the next task will be rejected. 

* The work queue is used to queue up tasks for the available worker threads. The queue can be bounded or unbounded. For bounded queues, setting the queue size is an important exercise, as it affects how the worker pool grows and when you start running into RejectedExecutionExceptions. If you have a work pool that you expect to grow; say from a core pool size of 20 workers to a max of 100 workers, then you may not want to set the queue size to a number that is too high, like 10,000, because it means that 10,000 tasks must be enqueued before each additional worker gets added to the pool. Unbounded queues and bounded queues with very high capacities are more suited to be used with fixed size pools (i.e. pools where the core and max pools sizes are the same). 

* If a thread pool grows to the max size, how does it shrink back to the core size? That's where the keepAliveTime comes in. If the current number of worker threads exceeds the core pool size and a keepAliveTime is set, then worker threads are shut down when there is no more work to do until the number of worker threads is back to the core pool size; a thread will wait for work for the keepAlive time, and when that is exceeded and no work arrives, it will shut down.

* Side note 1: You can set allowCoreThreadTimeOut to true on your ThreadPoolExecutor instance, and if you do so, then not only do workers threads that exceed the core pool size get shut down on idle, but core worker threads also get shut down on idle. By default, this is set to false.

* Side note 2: If your worker threads acquire and maintain expensive resources and only release those resources on shutdown, then it becomes important to optimally configure your keepAlive time. A keepAlive time of 0 ms means that your workers never shut down after they are created, unless the executor service itself is shut down.

* Most times, using the Default ThreadFactory is sufficient. The default thread factory creates worker threads that have a normal priority and are not daemon threads. It also gives the threads a name with the format: "pool-{poolNumber}-thread-{threadNumber}". If you want to customize any of these attributes, such as the thread name or priority, then you should provide your own threadFactory implementation. Another benefit of providing your own threadFactory implementation is that you can set the thread's uncaught exception handler, which can be very useful in combating silent failures. 

* Speaking of exceptions, I mentioned that for pools with a bounded work queue, task rejections occur when the queue is full and no more workers can be added. You can configure a handler to run when such a rejection occurs. These handlers are called "Policies". By default, the AbortPolicy is used, which throws a RejectedExecutionException. You can choose to use another Policy such as the DiscardPolicy, which simply discards the task silently; the CallerRunsPolicy, which executes the task on the calling thread instead of one of the worker threads; or any another policy implementation you create.

* To wrap up, the Java Executor Service hides a lot of complexity but also makes it easy for you to dive in and tweak the inner workings if you so choose. The Executors class provides a lot of factory methods that address different use cases; `newFixedThreadPool()` for when you just need a fixed number of threads that execute tasks, `newCachedThreadPool()` for when you want to create new threads as needed and shrink the pool when not needed, etc. In many cases, these pre-defined pools will meet your needs. However, if you have more defined parameters, then it helps to know some of the knobs you can tweak and how that affects the thread pool's behavior.

