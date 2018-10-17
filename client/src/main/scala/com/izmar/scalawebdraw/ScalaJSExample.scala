package com.izmar.scalawebdraw


import com.izmar.scalawebdraw.shared.SharedMessages
import org.scalajs.dom
import org.scalajs.dom.html.Canvas

object ScalaJSExample {

  val tree = new RedBlackTree

  def main(args: Array[String]): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    /*
    tree.put('G')
    tree.put('D')
    tree.put('E')
    tree.put('C')
    tree.put('I')
    tree.put('H')
    tree.put('J')
    tree.put('B')
    tree.put('F')
    tree.put('K')
    */


    tree.put('C')
    tree.put('A')
    //tree.put('B')

    drawRecursive(tree.root, Mut[Int](0), Mut[Int](0))

  }

  def drawRecursive(n: Mut[Node], yDepth: Mut[Int], xDepthOffset: Mut[Int]): Unit = {
    if (n.getMut != null) {

      val maxDepth = tree.findMaxDepth(n)

      drawNode(n.getMut, yDepth.getMut, maxDepth, xDepthOffset.getMut)

      if (n.getMut.left.getMut != null) {
        val yDepth1 = Mut[Int](yDepth.getMut + 1)
        val xDepthOffset1 = Mut[Int](xDepthOffset.getMut - maxDepth)
        drawRecursive(n.getMut.left, yDepth1, xDepthOffset1)
      }
      if (n.getMut.right.getMut != null) {
        val yDepth1 = Mut[Int](yDepth.getMut + 1)
        val xDepthOffset1 = Mut[Int](xDepthOffset.getMut + maxDepth)
        drawRecursive(n.getMut.right, yDepth1, xDepthOffset1)
      }
    }
  }

  def drawNode(n: Node, yDepth: Int, maxDepth: Int, xDepthOffset: Int): Unit = {
    println("Drawing node with key " + n.key.toInt)
    val canvas = dom.document.getElementById("canvas").asInstanceOf[Canvas]
    val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    context.font="30px Verdana";

    val radius = 30
    val lineHeight = 40
    val interval = radius + lineHeight
    val x = (canvas.width / 2) + (xDepthOffset * 2 * lineHeight)
    val y = radius + yDepth * interval + 2

    val maxDepth1 = if (maxDepth == 0) 0.5 else maxDepth
//    val maxDepth1 = maxDepth

    if (n.left.getMut != null && n.left.getMut.color == Node.RED) {
      context.strokeStyle = "#EE1818"
    } else {
      context.strokeStyle = "#000000"
    }

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(x - (lineHeight * maxDepth1 * 2), y + lineHeight + radius)
    context.stroke()

    if (n.right.getMut != null && n.right.getMut.color == Node.RED) {
      context.strokeStyle = "#EE1818"
    } else {
      context.strokeStyle = "#000000"
    }

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(x + (lineHeight * maxDepth1 * 2), y + lineHeight + radius)

    context.stroke()


    context.beginPath();
    context.arc(x,y,30,0,2*Math.PI);
    if (n.color == Node.RED) {
      context.strokeStyle = "#EE1818"
    } else {
      context.strokeStyle = "#000000"
    }
    context.stroke()
    context.fillStyle= "#FFFFFF"
    context.fill()

    context.textAlign = "center"
    context.textBaseline = "middle"
    if (n.color == Node.RED) {
      context.fillStyle = "#EE1818"

    } else {
      context.fillStyle = "#000000"
    }
    context.fillText(n.key.toString, x, y)
    context.strokeText(n.key.toString, x, y)

  }

}
