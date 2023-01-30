const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
// import HtmlWebpackPlugin from 'html-webpack-plugin';
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
// import MiniCssExtractPlugin from 'mini-css-extract-plugin';

module.exports = {
    entry: "./medecine/src/main/resources/static/js/main.js",
    output: {
        filename: "[name].js",
        path: path.resolve(__dirname, "medecine/src/main/resources/static/js/dist"),
        clean: true,
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|pages)/,
                use: {
                    loader: 'babel-loader',
                },
            },
            {
                test: /\.css$/,
                use: [
                    { loader: MiniCssExtractPlugin.loader },
                    {
                        loader: 'css-loader',
                        options: { import: true },
                    },
                ],
            },
            {
                test: /\.png$/,
                type: 'asset/resource',
            },
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({ template: './medecine/src/main/resources/static/index.html' }),
        new MiniCssExtractPlugin()
    ],
    devtool: 'eval-cheap-module-source-map',
    target: 'web',
    devServer: {
        contentBase: path.resolve(__dirname, 'medecine/src/main/resources/static/js/dist'),
        compress: true,
        hot: false,
        historyApiFallback: true,
        liveReload: true,
        open: true,
        port: 5500,
        watchContentBase: true,
        watchOptions: {
            poll: 1000,
            ignored: /node_modules/,
        },
    },
    mode: "development",
}