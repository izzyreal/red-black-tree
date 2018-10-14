package com.izmar.scalawebdraw


import com.izmar.scalawebdraw.shared.SharedMessages
import org.scalajs.dom
import org.scalajs.dom.html.Canvas

object ScalaJSExample {

  def main(args: Array[String]): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    val tree = new RedBlackTree
    tree.put(23.toChar, 23.toChar)
    tree.put(22.toChar, 22.toChar)
    tree.put(21.toChar, 21.toChar)
    tree.put(20.toChar, 20.toChar)
    tree.put(24.toChar, 24.toChar)
    tree.put(25.toChar, 25.toChar)

    drawRecursive(tree.root, Mut[Int](0), Mut[Int](0))

  }

  def drawRecursive(n: Mut[Node], xDepth: Mut[Int], yDepth: Mut[Int]): Unit = {
    if (n.getMut != null) {
      drawNode(n.getMut.key, n.getMut.color, xDepth.getMut, yDepth.getMut)
      if (n.getMut.left.getMut != null) {
        val xDepth1 = Mut[Int](xDepth.getMut - 1)
        val yDepth1 = Mut[Int](yDepth.getMut + 1)
        drawRecursive(n.getMut.left, xDepth1, yDepth1)
      }
      if (n.getMut.right.getMut != null) {
        val xDepth1 = Mut[Int](xDepth.getMut + 1)
        val yDepth1 = Mut[Int](yDepth.getMut + 1)
        drawRecursive(n.getMut.right, xDepth1, yDepth1)
      }
    }
  }

  def drawNode(key: Character, color: Boolean, xDepth: Int, yDepth: Int): Unit = {
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
    val x = (canvas.width / 2) + xDepth * interval
    val y = radius + yDepth * interval + 2

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(x - radius - lineHeight, y + lineHeight + radius)
    context.stroke()

    context.beginPath
    context.moveTo(x, y)
    context.lineTo(radius + x + lineHeight, y + lineHeight + radius)
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
