package com.example.androiddevchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.PuppyDto

class PuppyListViewModel : ViewModel(){
    private val _puppies = MutableLiveData(PuppyDto.getPuppies())
    val puppies : LiveData<List<PuppyDto>> = _puppies
}