package com.beloblotskiy

import org.apache.commons.io.FileUtils
import java.io.{File, PrintWriter}

/**
 * @author Aliaksei Belablotski
 */
object ListMyFiles {

  def listFolderAndSaveResult(startFolder: String, outputFile: String): Unit = {
    val files = for { f <- FileUtils.listFiles(new File(startFolder), null, true).toArray() } yield f.asInstanceOf[File]
    val file_size_list = for { f <- files } yield s"${f.getAbsoluteFile}\t${f.length()}"
    val writer = new PrintWriter(new File(outputFile))
    try {
      file_size_list.sorted.foreach { writer.println(_) }
      writer.println("-" * 80)
      writer.println(s"Number of files: ${file_size_list.length}, total size: ${files.map(_.length).reduce((x, y) => x + y)}")
    }
    finally {
      writer.close()
    }
  }
  
  def main(args: Array[String]): Unit = {
    //println(s"Command line arguments [${args.length}]:")
    //args foreach { x => println(x) }
    
    def listFolderAndSaveResult_w(startFolder: String): Unit = {
      println(s"Starting $startFolder")
      listFolderAndSaveResult(startFolder, "c:\\temp\\" + startFolder.replace(":", "").replace("\\", "_")) 
    }
    
    listFolderAndSaveResult_w("d:\\cc")
    listFolderAndSaveResult_w("d:\\Java")
    listFolderAndSaveResult_w("d:\\Projects")
    listFolderAndSaveResult_w("d:\\Projects2")
    listFolderAndSaveResult_w("d:\\Temp")
    listFolderAndSaveResult_w("d:\\Work")
  }
}