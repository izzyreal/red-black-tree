package com.izmar.scalawebdraw

case class Mut[A](var getMut: A = null.asInstanceOf[A]) {}

class Node(var key:Character, var value:Character) {

  var color = Node.RED

  var left : Mut[Node] = new Mut[Node](null)
  var right : Mut[Node] = new Mut[Node](null)

  // Number of nodes in this subtree
  var size : Int = 1

  // Height of this subtree
  var height : Int = 1

  // Coordinates for drawing
  var xc : Double = 0
  var yc : Double = 0

  def hasTwoChildren : Boolean = {
    left.getMut != null && right.getMut != null
  }

}

object Node{
  val RED = true
  val BLACK = false
}

class RedBlackTree {

  var root : Mut[Node] = new Mut[Node](null)

  def isRed(n: Mut[Node]) : Boolean = {
    // A null node is always black
    if (n.getMut == null) return false

    return n.getMut.color == Node.RED
  }

  def size() : Int = {
    size(root.getMut)
  }

  def size(n: Node) : Int = {
    if (n == null) return 0
    n.size
  }

  def height() : Int = {
    return height(root.getMut)
  }

  def height(n: Node) : Int = {
    if (n == null) return 0
    n.height
  }

  def rotateLeft(n: Mut[Node]): Unit = {
    ???
  }

  def rotateRight(n: Mut[Node]): Unit ={
    ???
  }

  def flipColors(n : Mut[Node]): Unit = {
    if (n.getMut == null) return

    val left = n.getMut.left.getMut
    val right = n.getMut.right.getMut

    // This method is only used when both children are red
    if (left != null && left.color == Node.RED) left.color = Node.BLACK
    if (right != null && right.color == Node.RED) right.color = Node.BLACK

  }

  def put(key: Character): Unit = {
    put(key, key)
  }

  def put(key: Character, value: Character): Unit = {
    put((root), key, value)
  }

  def put(n: Mut[Node], key: Character, value: Character): Mut[Node] = {

    println("putting " + key.toInt)

    if (n.getMut == null) {
      val newNode = new Node(key, value)
      if (root.getMut == null) newNode.color = Node.BLACK
      n.getMut = newNode

      return n
    }

    if (n.getMut.hasTwoChildren && isRed(n.getMut.left) && isRed(n.getMut.right)) flipColors(n)

    if (key < n.getMut.key) {
      put(n.getMut.left, key, value)
      println("Added left child and resulting key is " + n.getMut.left.getMut.key.toInt)
      return n.getMut.left
    } else if (key > n.getMut.key) {
      put(n.getMut.right, key, value)
      println("Added right child and resulting key is " + n.getMut.right.getMut.key.toInt)
      return n.getMut.right
    } else {
      ??? // handle case for when keys are equal
    }

  }

  def get(keyToFind: Character): Option[Character] = {
    var n = root
    while (n != null) {
      if (n.getMut.key == keyToFind) return Option(n.getMut.value)
      if (n.getMut.key > keyToFind) {
        n = n.getMut.left
      } else if (n.getMut.key < keyToFind) {
        n = n.getMut.right
      }
    }
    None
  }

  def findMaxDepth(n: Mut[Node]): Int = {
    return findMaxDepthRecursive(n)
  }

  def findMaxDepthRecursive(n: Mut[Node]): Int = {
    if (n.getMut == null) return -1
    return 1 + Math.max(findMaxDepthRecursive(n.getMut.left), findMaxDepthRecursive(n.getMut.right))
  }

}
