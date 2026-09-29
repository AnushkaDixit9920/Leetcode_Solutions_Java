class LRUCache {

    // ============================================================
    // STEP 1: Create a node for the Doubly Linked List
    // ============================================================

    // Each node stores the key, value and links to nearby nodes
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        // Create a node with the given key and value
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    // ============================================================
    // STEP 2: Create the required variables
    // ============================================================

    // HashMap helps us find a key in O(1) average time
    HashMap<Integer, Node> map = new HashMap<>();

    // Dummy head and tail help us easily manage the list
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);

    // Stores the maximum number of elements allowed
    int capacity;


    // ============================================================
    // STEP 3: Initialize the LRU Cache
    // ============================================================

    public LRUCache(int capacity) {

        // Store the given capacity
        this.capacity = capacity;

        // Initially, head and tail are connected
        head.next = tail;
        tail.prev = head;
    }


    // ============================================================
    // STEP 4: Add a node to the front
    // ============================================================

    // Front means the most recently used node
    private void addToFront(Node node) {

        // Connect the new node with the first actual node
        node.next = head.next;
        node.prev = head;

        // Update the previous first node
        head.next.prev = node;

        // Make the new node the first node
        head.next = node;
    }


    // ============================================================
    // STEP 5: Remove a node from the list
    // ============================================================

    private void removeNode(Node node) {

        // Connect the previous node directly to the next node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    // ============================================================
    // STEP 6: Get a value
    // ============================================================

    public int get(int key) {

        // Check whether the key exists in the cache
        if (!map.containsKey(key)) {

            // Key is not present
            return -1;
        }

        // Get the node directly from the HashMap
        Node node = map.get(key);

        // Since we just used this key,
        // remove it from its old position
        removeNode(node);

        // Move it to the front as most recently used
        addToFront(node);

        // Return its stored value
        return node.value;
    }


    // ============================================================
    // STEP 7: Put a key-value pair
    // ============================================================

    public void put(int key, int value) {

        // Check if the key already exists
        if (map.containsKey(key)) {

            // Get the existing node
            Node node = map.get(key);

            // Update its value
            node.value = value;

            // Move it to the front because it was just used
            removeNode(node);
            addToFront(node);

            return;
        }


        // ========================================================
        // STEP 8: Add a new key
        // ========================================================

        // Create a new node for this key-value pair
        Node newNode = new Node(key, value);

        // Store it in the HashMap for O(1) lookup
        map.put(key, newNode);

        // New item is the most recently used,
        // so put it at the front
        addToFront(newNode);


        // ========================================================
        // STEP 9: Remove the least recently used key
        // ========================================================

        // If we crossed the capacity,
        // remove the least recently used node
        if (map.size() > capacity) {

            // The node just before tail is the least recently used
            Node lruNode = tail.prev;

            // Remove it from the linked list
            removeNode(lruNode);

            // Remove its key from the HashMap
            map.remove(lruNode.key);
        }
    }
}