
## **1. Multithreading in Java**
### **1.1 Thread Class**
Java provides the `Thread` class, which can be extended to create a new thread.

#### **Example: Creating a Thread using the `Thread` Class**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();  // Starts a new thread
    }
}
```

---

### **1.2 Runnable Interface**
Instead of extending `Thread`, a class can implement the `Runnable` interface.

#### **Example: Creating a Thread using `Runnable`**
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running...");
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
    }
}
```

---

### **1.3 States of a Thread**
Threads can be in different states:
- **NEW**: Created but not started.
- **RUNNABLE**: Ready to run.
- **BLOCKED**: Waiting for a resource.
- **WAITING/TIMED_WAITING**: Waiting indefinitely or for a specified time.
- **TERMINATED**: Completed execution.

#### **Example: Checking Thread State**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running...");
    }
}

public class ThreadStates {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState()); // NEW
        t1.start();
        System.out.println(t1.getState()); // RUNNABLE
    }
}
```

---

## **2. Synchronization**
Synchronization ensures that multiple threads do not interfere with shared resources.

### **Example: Synchronizing a Method**
```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
```

---

## **3. Deadlock and Livelock**
### **3.1 Deadlock**
Occurs when two threads are waiting for each other to release locks.

#### **Example: Deadlock Scenario**
```java
class Resource {
    void methodA(Resource r) {
        synchronized (this) {
            System.out.println("Locked resource A");
            synchronized (r) {
                System.out.println("Locked resource B");
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        Thread t1 = new Thread(() -> r1.methodA(r2));
        Thread t2 = new Thread(() -> r2.methodA(r1));

        t1.start();
        t2.start();
    }
}
```

---

## **4. Time Complexity and Big-O Notation**
Time complexity measures how execution time increases with input size.

### **Example: Comparing O(n) and O(log n)**
```java
public class ComplexityDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // O(n) - Linear Search
        for (int num : arr) {
            System.out.println(num);
        }

        // O(log n) - Binary Search
        int target = 3;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                System.out.println("Found at index: " + mid);
                break;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
```

---

## **5. Searching Algorithms**
### **5.1 Linear Search (O(n))**
```java
public class LinearSearch {
    public static int search(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 40};
        System.out.println("Found at index: " + search(nums, 30));
    }
}
```

---

### **5.2 Binary Search (O(log n))**
```java
public class BinarySearch {
    public static int search(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 40, 50};
        System.out.println("Found at index: " + search(nums, 40));
    }
}
```

---

## **6. Reflection in Java**
Reflection allows inspecting and modifying classes, methods, and fields at runtime.

### **Example: Using Reflection to Get Method Names**
```java
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        Class<?> c = String.class;
        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
```

---

## **Conclusion**
- **Threads & Synchronization**: Multithreading improves performance but requires synchronization to avoid issues like deadlocks.
- **Big-O Notation**: Helps analyze performance, showing why algorithms like **binary search (O(log n))** are faster than **linear search (O(n))**.
- **Reflection**: Useful for inspecting and modifying Java classes dynamically.
