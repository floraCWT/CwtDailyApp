package com.fa.cwtdailyapp.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import com.fa.cwtdailyapp.data.DailyRepository
import com.fa.cwtdailyapp.data.entities.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
@OptIn(ExperimentalMaterialApi::class)
class DailyViewModel @Inject constructor(private val dailyRepository: DailyRepository) :
    ViewModel() {

    private val memberList = ArrayList<MutableState<Member>>()

    fun getMemberTeamListFrom(): ArrayList<MutableState<Member>> {

        val list = ArrayList<MutableState<Member>>()

        list.add(
            mutableStateOf(
                Member(
                    name = "Noam",
                    durationTalking = -1,
                    isAttending = false
                )
            )
        )
        list.add(
            mutableStateOf(
                Member(
                    name = "Kobi",
                    durationTalking = -1,
                    isAttending = false
                )
            )
        )

        return list
    }


    fun setMemberAttending(member: Member) {
    }


}
