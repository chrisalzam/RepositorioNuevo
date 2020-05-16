package com.example.threadstest

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class CustomLog: Timber.DebugTree() {
    private val LOG_TAG: String = CustomLog::class.java.simpleName

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        //region File
        try {
            val path = "Log"
            val fileNameTimeStamp = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
            val logTimeStamp =
                SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa", Locale.US).format(Date())
            val fileName = "$fileNameTimeStamp.html"

            //create file
            val file = generateFile(path, fileName)

            //if file created or exists save logs
            if (file != null) {
                val writer = FileWriter(file, true)
                writer.append(
                    "<p style=\"background:lightgray;\"><strong "
                            + "style=\"background:lightblue;\">&nbsp&nbsp"
                )
                    .append(logTimeStamp)
                    .append(" :&nbsp&nbsp</strong><strong>&nbsp&nbsp")
                    .append(tag)
                    .append("</strong> - ")
                    .append("<span>")
                    .append(message)
                    .append("</span>")
                    .append("</p>")
                writer.flush()
                writer.close()
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error while logging into file: $e")
        }
        //endregion

        super.log(priority, tag, message, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        return super.createStackElementTag(element) + " - " + element.lineNumber
    }

    private fun generateFile(path: String, fileName: String): File? {
        var file: File? = null

        if (isExternalStorageAvailable()) {
            val root = File(
                Environment.getExternalStorageDirectory().absoluteFile,
                BuildConfig.APPLICATION_ID + File.separator + path
            )

            var dirExists = true

            if (!root.exists()) {
                dirExists = root.mkdirs()
            }

            if (dirExists) {
                file = File(root, fileName)
                if (!file.exists()){
                    val writer = FileWriter(file, true)
                    writer.append("<script type=\"text/javascript\">\n" +
                            "    function filter() {\n" +
                            "        var input, filter, table, tr, td, cell, cell2, i, j;\n" +
                            "        input = document.getElementById(\"myInputFilter\");\n" +
                            "        filter = input.value.toUpperCase();\n" +
                            "        tr = document.getElementsByTagName(\"p\");\n" +
                            "        for (i = 0; i < tr.length; i++) {\n" +
                            "            tr[i].style.display = \"none\";\n" +
                            "            td = tr[i].getElementsByTagName(\"strong\");\n" +
                            "            for (var j = 0; j < td.length; j++) {\n" +
                            "                cell = tr[i].getElementsByTagName(\"strong\")[j];\n" +
                            "                if (cell) {\n" +
                            "                    if (cell.innerHTML.toUpperCase().indexOf(filter) > -1) {\n" +
                            "                        tr[i].style.display = \"\";\n" +
                            "                        break;\n" +
                            "                    }\n" +
                            "                }\n" +
                            "                cell2 = tr[i].getElementsByTagName(\"span\")[j];\n" +
                            "                if (cell2) {\n" +
                            "                    if (cell2.innerHTML.toUpperCase().indexOf(filter) > -1) {\n" +
                            "                        tr[i].style.display = \"\";\n" +
                            "                        break;\n" +
                            "                    }\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "</script>\n" +
                            "\n" +
                            "<input type=\"text\" id=\"myInputFilter\" onkeyup=\"filter()\" placeholder=\"Search for...\" title=\"Type in something\"\n" +
                            "    style=\"width: 100%; padding: 5px; font-size: 18px;\">")
                    writer.appendln("")
                    writer.flush()
                    writer.close()
                }
            }
        }

        return file
    }

    /* Helper method to determine if external storage is available*/
    private fun isExternalStorageAvailable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }
}