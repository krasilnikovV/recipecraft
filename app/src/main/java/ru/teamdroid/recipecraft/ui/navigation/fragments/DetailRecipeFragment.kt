package ru.teamdroid.recipecraft.ui.navigation.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import org.jetbrains.anko.bundleOf
import ru.teamdroid.recipecraft.R
import ru.teamdroid.recipecraft.data.model.Recipe
import ru.teamdroid.recipecraft.ui.base.BaseFragment
import ru.teamdroid.recipecraft.ui.navigation.adapters.IngredientsAdapter

class DetailRecipeFragment : BaseFragment() {

    override val contentResId = R.layout.fragment_detail_recipe

    private var recipes: Recipe? = null

    private val ingredientsAdapter by lazy {
        IngredientsAdapter(
                onItemClickListener = {}
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipes = it.getParcelable(RECIPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(toolbar, true, recipes?.title ?: "")

        with(recyclerView) {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        ingredientsAdapter.items = recipes?.ingredients ?: arrayListOf()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val RECIPE = "recipes"

        fun newInstance(recipes: Recipe) = DetailRecipeFragment().apply {
            arguments = bundleOf(RECIPE to recipes)
        }
    }
}