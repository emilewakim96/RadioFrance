package com.example.presentation.shows

import com.example.presentation.Action

class ShowsActionBuilder(
    private val executor: ShowsExecutor
) {

    fun seeUrl(id: String, url: String): Action<String> =
        Action(
            text = url,
            execute = {
                executor.seeShowUrl(id, url)
            }
        )
}