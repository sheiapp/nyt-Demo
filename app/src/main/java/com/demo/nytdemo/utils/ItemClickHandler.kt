package com.demo.nytdemo.utils

import com.demo.nytdemo.model.Result

interface ItemClickHandler {
    fun onItemClick(clickedData: Result)
}