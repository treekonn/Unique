package com.kigya.bsutools.data.api

import com.kigya.bsutools.data.models.Row
import com.kigya.bsutools.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import javax.inject.Inject

class RowApiImpl @Inject constructor() : RowApi {

    override suspend fun getAllRows(course: Int, group: Int): List<Row> {
        return withContext(Dispatchers.IO) {
            var doc: Document? = null
            try {
                doc =
                    Jsoup.connect(Constants.SCHEDULE_BASE_LINK + "$course-kurs/$group-gruppa/")
                        .get()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val tableList = emptyList<String>().toMutableList()
            val subjectTeacher = emptyList<Pair<String, String>>().toMutableList()
            doc?.getElementsByTag("tbody")?.forEach { table ->
                table.children()[0].siblingElements().forEach { row ->
                    row.children().forEach { cell ->
                        cell.select(".subject-teachers").forEach { child ->
                            val childString = child.toString()
                            val pair = childString.replace("<td class=\"subject-teachers\">", "")
                                .replace("</td>", "")
                                .split("<br>")
                            if (pair.size == 2) subjectTeacher.add(
                                Pair(
                                    pair[0],
                                    pair[1]
                                )
                            ) else subjectTeacher.add(Pair(pair[0], ""))
                        }
                        tableList.add(cell.text())
                    }
                }
            }
            val result = tableList.chunked(6)
            val rowList: MutableList<Row> = emptyList<Row>().toMutableList()
            result.forEachIndexed { index, list ->
                rowList.add(
                    Row(
                        day = list[0],
                        time = list[1],
                        group = list[2],
                        subject = subjectTeacher[index].first,
                        teacher = subjectTeacher[index].second,
                        type = list[4],
                        audience = list[5]
                    )
                )
            }
            return@withContext rowList
        }
    }
}