package com.izmar.scalawebdraw


import com.izmar.scalawebdraw.shared.SharedMessages
import org.scalajs.dom
import org.scalajs.dom.html.Canvas

object ScalaJSExample {

  val tree = new RedBlackTree

  def main(args: Array[String]): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    tree.put(23.toChar, 23.toChar)
    tree.put(22.toChar, 22.toChar)
    tree.put(21.toChar, 21.toChar)
    tree.put(20.toChar, 20.toChar)
    tree.put(24.toChar, 24.toChar)
    tree.put(25.toChar, 25.toChar)
    tree.put(26.toChar, 26.toChar)
//    tree.put(27.toChar, 27.toChar)

    drawRecursive(tree.root, Mut[Int](0), Mut[Int](0))

  }

  def drawRecursive(n: Mut[Node], yDepth: Mut[Int], xDepthOffset: Mut[Int]): Unit = {
    if (n.getMut != null) {

      val maxDepth = tree.findMaxDepth(n)

      drawNode(n.getMut.key, n.getMut.color, yDepth.getMut, maxDepth, xDepthOffset.getMut)

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

  def drawNode(key: Character, color: Boolean, yDepth: Int, maxDepth: Int, xDepthOffset: Int): Unit = {
    println("Drawing node with key " + key.toInt)
    val canvas = dom.document.getElementById("canvas").asInstanceOf[Canvas]
    val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    context.font="30px Verdana";
    if (color) {
      context.strokeStyle = "#EE1818"
      context.fillStyle = "#EE1818"

    } else {
      context.strokeStyle = "#000000"
      context.fillStyle = "#000000"
    }

    val radius = 30
    val lineHeight = 40
    val interval = radius + lineHeight
    val x = (canvas.width / 2) + xDepthOffset * lineHeight
    val y = radius + yDepth * interval + 2

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(x - radius - (lineHeight * maxDepth), y + lineHeight + radius)
    context.stroke()

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(radius + x + (lineHeight * maxDepth), y + lineHeight + radius)
    if (color) {
      context.strokeStyle = "#EE1818"

    } else {
      context.strokeStyle = "#000000"
    }
    context.stroke()


    context.beginPath();
    context.arc(x,y,30,0,2*Math.PI);
    context.strokeStyle = "#000000"
    context.stroke()
    context.fillStyle= "#FFFFFF"
    context.fill()

    context.textAlign = "center"
    context.textBaseline = "middle"
    if (color) {
      context.strokeStyle = "#EE1818"
      context.fillStyle = "#EE1818"

    } else {
      context.strokeStyle = "#000000"
      context.fillStyle = "#000000"
    }
    context.fillText(key.toInt.toString, x, y)
    context.strokeText(key.toInt.toString, x, y)

  }

}
