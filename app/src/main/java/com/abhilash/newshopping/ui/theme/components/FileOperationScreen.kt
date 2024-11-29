package com.abhilash.newshopping.ui.theme.components

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

fun saveToFile(context: Context, listName: String): Uri? {
    // Create ContentValues to insert into MediaStore
    val values = ContentValues().apply {
        put(MediaStore.Audio.Media.DISPLAY_NAME, "$listName.txt")
        put(MediaStore.Audio.Media.MIME_TYPE, "text/plain")
        put(MediaStore.Audio.Media.RELATIVE_PATH, "Documents/MyLists") // Specify a directory in shared storage
    }

    // Insert the new file into the MediaStore and get its URI
    return context.contentResolver.insert(MediaStore.Files.getContentUri("external"), values)
}
fun readFromFile(context: Context, uri: Uri): String {
    return try {
        context.contentResolver.openInputStream(uri)?.bufferedReader().use { reader ->
            reader?.readText() ?: "No content"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        "Error reading file"
    }
}
fun appendToFile(context: Context, data: String) {
    val filename = "data.txt" // Name of the file
    // Open the file output stream in append mode
    context.openFileOutput(filename, Context.MODE_APPEND).use { output ->
        output.write((data + "\n").toByteArray()) // Append data with a newline
    }
}
