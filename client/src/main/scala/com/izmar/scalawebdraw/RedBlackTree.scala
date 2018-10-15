package com.izmar.scalawebdraw

case class Mut[A](var getMut: A = null.asInstanceOf[A]) {}

class Node(var key:Character, var value:Character) {

  var color = Node.RED

  var left : Mut[Node] = new Mut[Node](null)
  var right : Mut[Node] = new Mut[Node](null)

}

object Node{
  val RED = true
  val BLACK = false
}

class RedBlackTree {

  var root : Mut[Node] = new Mut[Node](null)

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

    var yDepth = 0
    var subRoot = n.getMut

    while (subRoot != null) {
      if (subRoot.left.getMut != null) {
        subRoot = subRoot.left.getMut
        yDepth += 1
      } else if (subRoot.right.getMut != null) {
        subRoot = subRoot.right.getMut
        yDepth += 1
      } else {
        subRoot = null
      }
    }
    yDepth
  }

}
