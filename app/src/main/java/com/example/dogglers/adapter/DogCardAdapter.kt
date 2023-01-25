/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.databinding.GridListItemBinding
import com.example.dogglers.databinding.VerticalHorizontalListItemBinding
import com.example.dogglers.model.Dog
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource

    private val dataSource: List<Dog> = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        // TODO: Declare and initialize all of the list item UI components
        private lateinit var _imageView: ShapeableImageView
        private lateinit var _nameTextView: MaterialTextView
        private lateinit var _ageTextView: MaterialTextView
        private lateinit var _hobbiesTextView: MaterialTextView
        val imageView get() = _imageView
        val nameTextView get() = _nameTextView
        val ageTextView get() = _ageTextView
        val hobbiesTextView get() = _hobbiesTextView

        init {
            when (viewBinding) {
                is VerticalHorizontalListItemBinding -> {
                    _imageView = viewBinding.imageView
                    _nameTextView = viewBinding.nameTextView
                    _ageTextView = viewBinding.ageTextView
                    _hobbiesTextView = viewBinding.hobbiesTextView
                }
                is GridListItemBinding -> {
                    _imageView = viewBinding.imageView
                    _nameTextView = viewBinding.nameTextView
                    _ageTextView = viewBinding.ageTextView
                    _hobbiesTextView = viewBinding.hobbiesTextView
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // TODO: Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (layout) {
            Layout.VERTICAL, Layout.HORIZONTAL -> DogCardViewHolder(
                VerticalHorizontalListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            Layout.GRID -> DogCardViewHolder(
                GridListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("")
        }

        // TODO Inflate the layout

        // TODO: Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
    }

    override fun getItemCount(): Int =
        dataSource.size // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // TODO: Get the data at the current position
        // TODO: Set the image resource for the current dog
        // TODO: Set the text for the current dog's name
        // TODO: Set the text for the current dog's age
        val dog = dataSource[position]
        val resources = context?.resources
        holder.apply {
            imageView.setImageResource(dog.imageResourceId)
            nameTextView.text = dog.name
            ageTextView.text = resources?.getString(R.string.dog_age, dog.age)
            hobbiesTextView.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)
        }
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
