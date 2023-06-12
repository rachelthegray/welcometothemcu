package com.example.myapplication

import android.content.res.Resources

/* Returns initial list of Marvels. */
fun MarvelList(resources: Resources): List<Marvel> {
    return listOf(
        Marvel(
            id = 1,
            name = resources.getString(R.string.loki_name),
            image = R.drawable.loki,
            description = resources.getString(R.string.loki_description)
        ),
        Marvel(
            id = 2,
            name = resources.getString(R.string.tony_name),
            image = R.drawable.tony_stark,
            description = resources.getString(R.string.tony_description)
        ),
        Marvel(
            id = 3,
            name = resources.getString(R.string.thanos_name),
            image = R.drawable.thanos,
            description = resources.getString(R.string.thanos_description)
        ),
        Marvel(
            id = 4,
            name = resources.getString(R.string.doctor_strange_name),
            image = R.drawable.doctor_strange,
            description = resources.getString(R.string.doctor_strange_description)
        ),
        Marvel(
            id = 5,
            name = resources.getString(R.string.scarlet_witch_name),
            image = R.drawable.scarlet_witch,
            description = resources.getString(R.string.scarlet_witch_description)
        ),
        Marvel(
            id = 6,
            name = resources.getString(R.string.hawkeye_name),
            image = R.drawable.hawkeye,
            description = resources.getString(R.string.hawkeye_description)
        ),
        Marvel(
            id = 7,
            name = resources.getString(R.string.hulk_name),
            image = R.drawable.hulk,
            description = resources.getString(R.string.hulk_description)
        ),
        Marvel(
            id = 8,
            name = resources.getString(R.string.black_widow_name),
            image = R.drawable.black_widow,
            description = resources.getString(R.string.black_widow_description)
        ),
        Marvel(
            id = 9,
            name = resources.getString(R.string.captain_america_name),
            image = R.drawable.captain_america,
            description = resources.getString(R.string.captain_america_description)
        ),
        Marvel(
            id = 10,
            name = resources.getString(R.string.thor_name),
            image = R.drawable.thor,
            description = resources.getString(R.string.thor_description)
        ),
    )
}