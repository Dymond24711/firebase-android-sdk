// Copyright 2022 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.firestore;

/**
 * The sources from which an {@link AggregateQuery} can retrieve its results.
 *
 * @see AggregateQuery#get
 */
public enum AggregateSource {
  /**
   * Perform the aggregation on the server and download the result.
   *
   * <p>The result received from the server is presented, unaltered, without considering any local
   * state. That is, documents in the local cache are not taken into consideration, neither are
   * local modifications not yet synchronized with the server. Previously-downloaded results, if
   * any, are not used: every request using this source necessarily involves a round trip to the
   * server.
   *
   * <p>The {@link AggregateQuery} will fail if the server cannot be reached, such as if the client
   * is offline.
   */
  SERVER,

  /**
   * Perform or estimate the aggregations over the documents in the result set, based on data in
   * the SDK's cache.
   *
   * <p>If there IS NOT a cached aggregation result for the specified
   * query and AggregateField from a previous AggregateQuery sent
   * to the server, then the returned aggregation value will be computed against
   * documents in the SDK's local cache.
   *
   * <p>If there IS a cached aggregation result for the specified
   * query and AggregateField from a previous AggregateQuery sent
   * to the server, then the returned aggregation value will be computed by
   * augmenting the cached aggregation value against document mutations in the
   * SDK's local cache. The SDK attempts to compute the most accurate aggregation
   * values from these two sources by utilizing timestamps on the cached
   * aggregation values and the cached document mutations.
   *
   * <p>If cached aggregation values are not available for all of the requested
   * AggregateFields, or if cached aggregation values do not have the same timestamp
   * for all of the requested aggregate fields, then TODO?
   *   - The SDK computes aggregations based on the most recent cached aggregation
   *     value it has in the cache.
   *   - The SDK ignore cached aggregation values and computes each aggregation
   *     based on the documents in the cache.
   */
  CACHE,


  /**
   * Perform the aggregation on the server and download the result, but fall
   * back to computing the aggregation base on cached data if the server can't be reached.
   *
   * TODO Will DEFAULT mode apply pending writes to the Aggregation result?
   */
  DEFAULT,
}
